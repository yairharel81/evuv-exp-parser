package com.evuv.exceptions;

public class EventBindingException extends Exception {

	private static final long serialVersionUID = 1L;

	public EventBindingException(String msg) {
		super(msg);
	}
	
	public EventBindingException(Exception ex) {
		super(ex);
	}

}
