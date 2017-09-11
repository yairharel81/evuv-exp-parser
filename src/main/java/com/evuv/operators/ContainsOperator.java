package com.evuv.operators;

import com.evuv.expressions.BindedExpression;

public class ContainsOperator<T> implements Operator<BindedExpression<String>, String > {

	@Override
	public boolean op(BindedExpression<String> left, BindedExpression<String> right) {
		if (left.getValue() != null && right.getValue() != null) {
			return left.getValue().contains(right.getValue());
		}
		return left.getValue() == null && right.getValue() == null;
	}
	
	@Override
	public String toString(){
		return "contains";
	}

	

	

}
