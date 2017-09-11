package com.evuv.expressions;


import java.util.List;
import java.util.Map;

import com.evuv.exceptions.EventBindingException;
import com.evuv.operators.ContainsOperator;

public class ContainsExpression<T>  implements Expression<Boolean>, BindedExpression<Boolean> {

	protected ContainsOperator<String> operator;
	
	Expression<String> left;
	Expression<String> right;
	
	public  ContainsExpression(){}
	public  ContainsExpression(Expression<String> left, Expression<String> right) {
		this.left = left;
		this.right = right;
		operator = new ContainsOperator<String>();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() {
		return operator.op((BindedExpression<String>)left, (BindedExpression<String>)right);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public Expression getLeft() {
		return left;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setLeft(Expression left) {
		this.left = left;		
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Expression getRight() {
		return right;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setRight(Expression right) {
		this.right = right;
	}
	
	@Override
	public  List<String> toFlatExression() {
		return BaseExpression.toFlatExression(left, right, operator);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ContainsExpression<String> bind(Map<String, Object> event) throws EventBindingException {
		BindedExpression<String> bindedLeft = left.bind(event);
		BindedExpression<String> bindedRight =  right.bind(event);
		return new ContainsExpression<>((Expression<String>)bindedLeft, (Expression<String>)bindedRight);
	}
	

}
