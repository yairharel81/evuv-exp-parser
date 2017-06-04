package com.evuv.operators;

import com.evuv.expressions.ComparableExpression;

public class BiggerThanOperator<T> implements Operator<ComparableExpression<T>, T> {


	@Override
	public boolean op(ComparableExpression<T> left, ComparableExpression<T> right) {
		return left.compareTo(right.getValue()) > 0;
	}

	@Override
	public String toString(){
		return ">";
	}

}
