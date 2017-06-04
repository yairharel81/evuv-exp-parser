package com.evuv.operators;

import com.evuv.expressions.BindedExpression;

public interface Operator<T extends BindedExpression<U>, U> {

	public boolean op(T left, T right);
}
