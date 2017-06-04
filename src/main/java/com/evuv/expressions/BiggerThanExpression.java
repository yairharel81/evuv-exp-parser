package com.evuv.expressions;



import java.util.Map;

import com.evuv.exceptions.EventBindingException;
import com.evuv.operators.BiggerThanOperator;
import com.evuv.operators.Operator;

public class BiggerThanExpression<T> extends BooleanExpression<T> {

	protected BiggerThanOperator<T> operator;
	
	
	public BiggerThanExpression(ComparableExpression<T> left, ComparableExpression<T> right) {
		super(left, right);
		operator = new BiggerThanOperator<T>();
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
	public BiggerThanExpression<T> bind(Map<String, Object> event) throws EventBindingException {
		ComparableExpression<T> bindedLeft = (ComparableExpression<T>) left.bind(event);
		ComparableExpression<T> bindedRight = (ComparableExpression<T>) right.bind(event);
		return new BiggerThanExpression<>(bindedLeft, bindedRight);
	}

}
