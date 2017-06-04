package com.evuv.expressions;


import java.util.Map;

import com.evuv.exceptions.EventBindingException;
import com.evuv.operators.Operator;
import com.evuv.operators.SmallerThanOperator;

public class SmallerThanExpression<T> extends BooleanExpression<T> {

	protected SmallerThanOperator<T> operator;
	
	
	public SmallerThanExpression(ComparableExpression<T> left, ComparableExpression<T> right) {
		super(left, right);
		operator = new SmallerThanOperator<T>();
	}
	
	
	@Override
	public Boolean getValue() {
		return operator.op(left, right);
	}


	@SuppressWarnings("rawtypes")
	@Override
	protected Operator getOperator() {
		return operator;
	}
	
	@Override
	public SmallerThanExpression<T> bind(Map<String, Object> event) throws EventBindingException {
		ComparableExpression<T> bindedLeft = (ComparableExpression<T>) left.bind(event);
		ComparableExpression<T> bindedRight = (ComparableExpression<T>) right.bind(event);
		return new SmallerThanExpression<>(bindedLeft, bindedRight);
	}

}
