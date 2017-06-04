package com.evuv.expressions;

import java.util.LinkedList;
import java.util.List;

import com.evuv.operators.Operator;


public interface BaseExpression {

	
	@SuppressWarnings("rawtypes")
	public Expression getLeft();
	
	
	
	@SuppressWarnings("rawtypes")
	public Expression getRight();
	
	public default List<String> toFlatExression() {
		List<String> res = new LinkedList<String>();
		List<String> leftExpr = getLeft().toFlatExression();
		if (leftExpr != null ) {
			res.addAll(leftExpr);
		}
		List<String> rightExpr = getRight().toFlatExression();
		if (rightExpr != null ) {
			res.addAll(rightExpr);
		}
		return res;
	}
	
	@SuppressWarnings("rawtypes")
	static List<String> toFlatExression(Expression left, Expression right, Operator operator) {
		List<String> res = new LinkedList<String>();

		if (left instanceof SimplePropertyExpression && right instanceof SimpleValueExpression) {
			StringBuilder sb = new StringBuilder();
			SimplePropertyExpression p = (SimplePropertyExpression) left;
			SimpleValueExpression v = (SimpleValueExpression) right;
			sb.append(p.toString()).append(" ").append(operator.toString()).append(" ").append(v.getValue());
			res.add(sb.toString());
		}

		if (right instanceof SimplePropertyExpression && left instanceof SimpleValueExpression) {
			StringBuilder sb = new StringBuilder();
			SimplePropertyExpression p = (SimplePropertyExpression) right;
			SimpleValueExpression v = (SimpleValueExpression) left;
			sb.append(p.toString()).append(" ").append(operator.toString()).append(" ").append(v.getValue());
			res.add(sb.toString());
		}
		return res;
	}
}
