package com.evuv.expressions;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.evuv.exceptions.EventBindingException;



public class AndExpression implements Expression<Boolean>, BindedExpression<Boolean> {

	
	protected Expression<Boolean> left;
	
	protected Expression<Boolean> right;
	
	public AndExpression(){}
	public AndExpression(Expression<Boolean> left, Expression<Boolean> right) {
		this.left = left;
		this.right = right;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() {
		return ((BindedExpression<Boolean>)left).getValue() && ((BindedExpression<Boolean>)right).getValue();
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
	@Override
	public List<String> toFlatExression() {
		List<String> res = new LinkedList<String>();
		List<String> leftExpr = left.toFlatExression();
		if (leftExpr != null ) {
			res.addAll(leftExpr);
		}
		List<String> rightExpr = right.toFlatExression();
		if (rightExpr != null ) {
			res.addAll(rightExpr);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AndExpression bind(Map<String, Object> event) throws EventBindingException {
		BindedExpression<Boolean> bindedLeft = left.bind(event);
		BindedExpression<Boolean> bindedRight = right.bind(event);
		return new AndExpression((Expression<Boolean>)bindedLeft, (Expression<Boolean>)bindedRight);
	}
	
	

}
