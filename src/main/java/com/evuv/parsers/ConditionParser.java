package com.evuv.parsers;


import org.json.JSONException;

import com.evuv.exceptions.ParserException;
import com.evuv.parsers.converters.PropertyNamingConverter;




public interface ConditionParser<T> {

	public static final String TENANT_ID = "tenant_id";
	public static final String TIMESTAMP_PROP_NAME = "timestamp";
	public static final String AGGREGATIONS_NAME_KEY = "name";
	public static final String RESPONSE_EVENT_KEY = "event";
	public static final String AGGREGATIONS_KEY = "aggregations";
	public static final String GRANULARITY_KEY = "granularity";
	public static final String GRANULARITY_ALL = "all";
	public static final String DEFAULT_COOLADATA_DATA_SOURCE = "Cooladata";
	public static final String DIMENSIONS_KEY = "dimensions";
	public static final String SINGLE_DIMENSION_KEY = "dimension";
	public static final String SINGLE_MEASURE_KEY = "measure";
	public static final String FILTER_FIELDS_KEY = "fields";
	public static final String FILTER_TYPE_KEY = "type";
	
	public static final String FILTER_NOT_OP = "not";
	
	public static final String MEASURE_TYPE = "measure";
	public static final String SELECTOR_TYPE = "selector";
	
	public static final String DRUID_QUERY_JSON_DIMENSIONS_PROPNAME = "dimensions";
	public static final String DRUID_QUERY_JSON_FILTER_PROPNAME = "filter";
	public  static final String DRUID_TIME_FORMAT  = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String FILTER_FIELD_KEY = "field";
	

	public abstract T parseCondition(String json, PropertyNamingConverter converter, 
			 boolean bind) throws ParserException, JSONException;
	
	public abstract T parseCondition(String json,
			 boolean bind) throws ParserException, JSONException;
	

}
