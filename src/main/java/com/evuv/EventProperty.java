package com.evuv;

public class EventProperty<T> {

	private String propertyName;
	private T propertyValue;
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public T getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(T propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	
}
