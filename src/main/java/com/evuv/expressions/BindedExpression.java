package com.evuv.expressions;

public interface BindedExpression<T> extends BaseExpression {

	public T getValue();
}
