package com.evuv.exceptions;

public class ParserException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParserException(String msg) {
		super(msg);
	}
	
	public ParserException(Exception ex) {
		super(ex);
	}
}
