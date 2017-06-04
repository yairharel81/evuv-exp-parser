package com.evuv.expressions;

import java.util.Map;

import com.evuv.exceptions.EventBindingException;

public class OrExpression  implements Expression<Boolean>, BindedExpression<Boolean> {

	
	protected Expression<Boolean> left;
	
	protected Expression<Boolean> right;
	
	public OrExpression(){}
	public OrExpression(Expression<Boolean> left, Expression<Boolean> right) {
		this.left = left;
		this.right = right;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() {
		return ((BindedExpression<Boolean>)left).getValue() || ((BindedExpression<Boolean>)right).getValue();
	}

	public Expression<Boolean> getLeft() {
		return left;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression<Boolean> getRight() {
		return right;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setRight(Expression right) {
		this.right = right;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public OrExpression bind(Map<String, Object> event) throws EventBindingException {
		BindedExpression<Boolean> bindedLeft = left.bind(event);
		BindedExpression<Boolean> bindedRight = right.bind(event);
		return new OrExpression((Expression<Boolean>)bindedLeft, (Expression<Boolean>)bindedRight);
	}
	

}
