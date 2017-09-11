package com.evuv.operators;

import com.evuv.expressions.ComparableExpression;

public class SmallerThanOperator<T> implements Operator<ComparableExpression<T>, T> {


	@Override
	public boolean op(ComparableExpression<T> left, ComparableExpression<T> right) {
		if (left.getValue() != null && right.getValue() != null) {
			return left.compareTo(right.getValue()) < 0;
		}
		return false;
	}

	@Override
	public String toString(){
		return "<";
	}

}
