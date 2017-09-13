package com.evuv.operators;

import com.evuv.expressions.BindedExpression;

public class ExistsOperator <T> implements Operator<BindedExpression<T>, T > {
	@Override
	public boolean op(BindedExpression<T> left, BindedExpression<T> right) {
		return left.getValue() != null;
	}
	
	@Override
	public String toString(){
		return "exists";
	}
}
