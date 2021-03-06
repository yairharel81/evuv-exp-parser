package com.evuv.operators;

import com.evuv.expressions.BindedExpression;

public class EqualOperator<T> implements Operator<BindedExpression<T>, T > {

	
	
	@Override
	public boolean op(BindedExpression<T> left, BindedExpression<T> right) {
		if (left.getValue() != null && right.getValue() != null) {
			return left.getValue().equals(right.getValue());
		}
		return left.getValue() == null && right.getValue() == null;
	}
	
	@Override
	public String toString(){
		return "=";
	}

}
