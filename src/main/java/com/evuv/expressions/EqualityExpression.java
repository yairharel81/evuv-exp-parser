package com.evuv.expressions;


import java.util.List;
import java.util.Map;

import com.evuv.exceptions.EventBindingException;
import com.evuv.operators.EqualOperator;

public class EqualityExpression<T>  implements Expression<Boolean>, BindedExpression<Boolean> {

	protected EqualOperator<T> operator;
	
	Expression<T> left;
	Expression<T> right;
	
	public  EqualityExpression(){}
	public  EqualityExpression(Expression<T> left, Expression<T> right) {
		this.left = left;
		this.right = right;
		operator = new EqualOperator<T>();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() {
		return operator.op((BindedExpression<T>)left, (BindedExpression<T>)right);
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
	public EqualityExpression<T> bind(Map<String, Object> event) throws EventBindingException {
		BindedExpression<T> bindedLeft = left.bind(event);
		BindedExpression<T> bindedRight =  right.bind(event);
		return new EqualityExpression<>((Expression<T>)bindedLeft, (Expression<T>)bindedRight);
	}
	

}
