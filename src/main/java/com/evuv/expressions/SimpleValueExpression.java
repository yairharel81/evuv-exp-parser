package com.evuv.expressions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.evuv.exceptions.EventBindingException;

public class SimpleValueExpression<T extends Comparable<T>>  implements ComparableExpression<T>, BindedExpression<T> {

	
    private T value;
	
    public SimpleValueExpression(T val) {
    	this.value = val;
    }
	
	@Override
	public T getValue() {
		return value;
	}

	@Override
	public int compareTo(T o) {
		return value.compareTo(o);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Expression getLeft() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setLeft(Expression left) {		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Expression getRight() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setRight(Expression right) {		
	}
	public List<String> toFlatExression() {
		return Collections.emptyList();
	}

	@Override
	public SimpleValueExpression<T> bind(Map<String, Object> event) throws EventBindingException {
		return new SimpleValueExpression<>(value);
	}

}
