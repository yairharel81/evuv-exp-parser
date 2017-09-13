package com.evuv.expressions;


import java.util.List;
import java.util.Map;

import com.evuv.exceptions.EventBindingException;
import com.evuv.operators.ExistsOperator;

public class ExistsExpression<T>  implements Expression<Boolean>, BindedExpression<Boolean> {

	protected ExistsOperator<T> operator;
	
	@SuppressWarnings("rawtypes")
	SimplePropertyExpression left;
	
	public  ExistsExpression(){}
	@SuppressWarnings("rawtypes")
	public  ExistsExpression(SimplePropertyExpression left) {
		this.left = left;
		operator = new ExistsOperator<T>();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() {
		return operator.op(left, left);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public Expression getLeft() {
		return left;
	}


	@SuppressWarnings({ "rawtypes" })
	@Override
	public void setLeft(Expression left) {
		if ( left instanceof SimplePropertyExpression) {
			this.left = (SimplePropertyExpression)left;
		}
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public Expression getRight() {
		return null;
	}


	@SuppressWarnings({ "rawtypes"})
	@Override
	public void setRight(Expression right) {}
	
	@Override
	public  List<String> toFlatExression() {
		return BaseExpression.toFlatExression(left, left, operator);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ExistsExpression<String> bind(Map<String, Object> event) throws EventBindingException {
		SimplePropertyExpression<String> bindedLeft = left.bind(event);
		return new ExistsExpression<>(bindedLeft);
	}

	

}
