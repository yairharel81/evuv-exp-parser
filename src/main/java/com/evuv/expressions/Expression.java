package com.evuv.expressions;

import java.util.Map;

import com.evuv.exceptions.EventBindingException;


public interface Expression<T> extends BaseExpression {
	


	@SuppressWarnings("rawtypes")
	public void setLeft(Expression left);

	@SuppressWarnings("rawtypes")
	public void setRight(Expression right);
	
	public BindedExpression<T> bind(Map<String, Object> event) throws EventBindingException;
	
}
	
