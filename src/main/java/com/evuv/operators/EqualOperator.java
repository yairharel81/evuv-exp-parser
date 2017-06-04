package com.evuv.operators;

import com.evuv.expressions.BindedExpression;

public class EqualOperator<T> implements Operator<BindedExpression<T>, T > {

	
	
	@Override
	public boolean op(BindedExpression<T> left, BindedExpression<T> right) {
		return left.getValue().equals(right.getValue());
	}
	
	@Override
	public String toString(){
		return "=";
	}

}
