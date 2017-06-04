package com.evuv.expressions;

import java.util.List;

import com.evuv.operators.Operator;

public abstract class BooleanExpression<T>  implements Expression<Boolean>, BindedExpression<Boolean> {

	protected ComparableExpression<T> left;
	
	protected ComparableExpression<T> right;
	
	public BooleanExpression(ComparableExpression<T> left, ComparableExpression<T>  right) {
		this.left = left;
		this.right = right;
	}
	
	public Expression<T> getLeft() {
		return left;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setLeft(Expression left) {
		this.left = (ComparableExpression<T>) left;
	}

	public Expression<T> getRight() {
		return right;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setRight(Expression right) {
		this.right = (ComparableExpression<T>) right;
	}
	
	@SuppressWarnings("rawtypes")
	protected abstract Operator getOperator();
	
	@Override
	public  List<String> toFlatExression() {
		return BaseExpression.toFlatExression(left, right, getOperator());
	}
	
	
	
}
