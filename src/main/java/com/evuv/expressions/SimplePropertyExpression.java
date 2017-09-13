package com.evuv.expressions;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.evuv.EventProperty;
import com.evuv.GenericNumber;
import com.evuv.exceptions.EventBindingException;

public class SimplePropertyExpression<T extends Comparable<T>>  implements ComparableExpression<T>, BindedExpression<T> {

	private EventProperty<T> ev;
	
	public SimplePropertyExpression(EventProperty<T> ev) {
		this.ev = ev;
	}
	
	@Override
	public T getValue() {
		return ev.getPropertyValue();
	}

	@Override
	public int compareTo(T o) {
		return ev.getPropertyValue().compareTo(o);
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
	public String toString() {
		return ev.getPropertyName();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SimplePropertyExpression<T> bind(Map<String, Object> event) throws EventBindingException {
		Object value = event.get(ev.getPropertyName());
		EventProperty<T> newEv = new EventProperty<>();
		newEv.setPropertyName(ev.getPropertyName());
		if ( value == null ) {
			// support missing value
			newEv.setPropertyValue(null);
		} else  if (value instanceof Number) {
			newEv.setPropertyValue((T) new GenericNumber(value));
		} else if (value instanceof String ) {
			newEv.setPropertyValue((T) value);
		} else {
			throw new EventBindingException (String.format("cannot bind %s to value %s", ev.getPropertyName(), value));
		}
		return new SimplePropertyExpression<>(newEv);
	}


}
