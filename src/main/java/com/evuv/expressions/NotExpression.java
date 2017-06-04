package com.evuv.expressions;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.evuv.exceptions.EventBindingException;


public class NotExpression implements Expression<Boolean>, BindedExpression<Boolean> {

	protected Expression<Boolean> node;

	public NotExpression(){}
	public NotExpression(Expression<Boolean> node) {
		this.node = node;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() {
		return !((BindedExpression<Boolean>)node).getValue();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Expression getLeft() {
		return node;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setLeft(Expression left) {
		this.node = left;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Expression getRight() {
		return node;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setRight(Expression right) {
		this.node = right;
	}
	
	@Override
	public List<String> toFlatExression() {
		List<String> res = new LinkedList<String>();
		List<String> nodeExpr = node.toFlatExression();
		if (nodeExpr != null ) {
			res.addAll(nodeExpr);
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public NotExpression bind(Map<String, Object> event) throws EventBindingException {
		BindedExpression<Boolean> newNode = node.bind(event);
		return new NotExpression((Expression<Boolean>)newNode);
	}
}
