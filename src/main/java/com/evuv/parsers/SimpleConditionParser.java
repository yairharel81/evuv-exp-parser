package com.evuv.parsers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.evuv.EventProperty;
import com.evuv.GenericNumber;
import com.evuv.exceptions.ParserException;
import com.evuv.expressions.AndExpression;
import com.evuv.expressions.BiggerThanExpression;
import com.evuv.expressions.EqualityExpression;
import com.evuv.expressions.Expression;
import com.evuv.expressions.NotExpression;
import com.evuv.expressions.OrExpression;
import com.evuv.expressions.SimplePropertyExpression;
import com.evuv.expressions.SimpleValueExpression;
import com.evuv.expressions.SmallerThanExpression;
import com.evuv.parsers.converters.DefaultPropertyNamingConverter;
import com.evuv.parsers.converters.PropertyNamingConverter;


/**
 * Parser the JSON object of a condition
 */
public class SimpleConditionParser implements ConditionParser<Expression<Boolean>> {

	
	
	@Override
	public Expression<Boolean> parseCondition(String json, boolean test)
			throws JSONException, ParserException {
		
		return parseCondition(json, new DefaultPropertyNamingConverter(), test);
	}
	
	@Override
	public Expression<Boolean> parseCondition(String json, PropertyNamingConverter converter, boolean test)
			throws JSONException, ParserException {
		
		JSONObject obj = new JSONObject(json);
		JSONObject filter = obj.optJSONObject(DRUID_QUERY_JSON_FILTER_PROPNAME);
		return parseJsonObject(filter,converter, test);
	}
	
	
	
	public Expression<Boolean> validateQuery(JSONObject queryObj) throws JSONException, ParserException {
		// get the filter object
		JSONObject filter = queryObj.optJSONObject(DRUID_QUERY_JSON_FILTER_PROPNAME);
		return parseJsonObject(filter, null, true);
	}
	
	private Expression<Boolean> parseJsonObject(JSONObject filter, PropertyNamingConverter converter, 
		 boolean test) throws JSONException, ParserException {
		if (filter != null) {
			String type = filter.getString(FILTER_TYPE_KEY);
			if (type.equals(SELECTOR_TYPE) && filter.has(SINGLE_DIMENSION_KEY)) {
				return createDimenssionExpression(filter, converter, test);
			} else if (type.equals(MEASURE_TYPE) && filter.has(SINGLE_MEASURE_KEY)) {
				return createMeasureExpression(filter, converter, test);
			} else if (filter.has(FILTER_FIELDS_KEY)) {
				return createCompositeOrAndExpression(filter, converter, test);
			} else if (filter.has(FILTER_FIELD_KEY) && type.equals(FILTER_NOT_OP)) {
				JSONObject field = filter.getJSONObject(FILTER_FIELD_KEY);
				Expression<Boolean> inner = parseJsonObject(field, converter, test);
				Expression<Boolean> expr = new NotExpression();
				expr.setLeft(inner);
				return expr;
			}
		}
		throw new ParserException(String.format("cannot handle filter %s ", filter));
	}
	
	private Expression<Boolean> createMeasureExpression(JSONObject filter, PropertyNamingConverter converter, 
			 boolean test) throws ParserException {
		String op = filter.getString("op");
		String propertyName = filter.getString(SINGLE_MEASURE_KEY);
		EventProperty<GenericNumber> ev = new EventProperty<GenericNumber>();
		if (!test) {
			ev.setPropertyName(converter.convert(propertyName));
		}
		SimplePropertyExpression<GenericNumber> left = new SimplePropertyExpression<GenericNumber>(ev);
		GenericNumber expectedValue = new GenericNumber (filter.get("value"));
		SimpleValueExpression<GenericNumber> right = new SimpleValueExpression<GenericNumber>(expectedValue);
		Expression<Boolean> expr = getSImpleOperatorExpression(op, left, right);
		return expr;
	}
	
	private Expression<Boolean> createDimenssionExpression(JSONObject filter, PropertyNamingConverter converter, 
			 boolean test) throws ParserException {
		String propertyName = filter.getString(SINGLE_DIMENSION_KEY);
		EventProperty<String> ev = new EventProperty<String>();
		
		if (!test) {
			ev.setPropertyName(converter.convert(propertyName));
		}
		SimplePropertyExpression<String> left = new SimplePropertyExpression<String>(ev);
		String expectedValue = filter.getString("value");
		SimpleValueExpression<String> right = new SimpleValueExpression<String>(expectedValue);
		Expression<Boolean> expr = new EqualityExpression<String>(left, right);
		return expr;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Expression<Boolean> createCompositeOrAndExpression(JSONObject filter, PropertyNamingConverter converter, 
			 boolean test) throws ParserException {
		String type = filter.getString(FILTER_TYPE_KEY);
		JSONArray fields = filter.getJSONArray(FILTER_FIELDS_KEY);
		Expression<Boolean> expr = getAndOrExpression(type);
		Expression<Boolean> currentExpression = expr;
		Expression left = null;
		Expression right = null;
		for (int i = 0; i < fields.length(); i++) {
			Expression<Boolean> inner = parseJsonObject(fields.getJSONObject(i), converter, test);
			if (left == null ) {
				left = inner;
				currentExpression.setLeft(left);
			} else if (right == null && i < (fields.length() -1)) {
				right = getAndOrExpression(type); 
				currentExpression.setRight(right);
				right.setLeft(inner);
				currentExpression = right;
				right = null;
			} else if (right == null) {
				currentExpression.setRight(inner);
			}
		}
		return expr;
	}
	

	private Expression<Boolean> getAndOrExpression(String type) throws ParserException {
		switch(type) {
		case "and" : return new AndExpression(); 
		case "or" : return new OrExpression(); 
		}
		throw new ParserException("operator not supported " + type);
	}
	
	
	
	private Expression<Boolean> getSImpleOperatorExpression(String op, 
			SimplePropertyExpression<GenericNumber> left, SimpleValueExpression<GenericNumber> right) throws ParserException {
		switch(op) {
		case "=" : return new EqualityExpression<>(left, right); 
		case "<" : return new SmallerThanExpression<>(left, right); 
		case ">" : return new BiggerThanExpression<>(left, right); 
		default:
			throw new ParserException("operation not supported " + op);
		}
	}
}
