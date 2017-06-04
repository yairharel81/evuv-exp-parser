package com.evuv.exceptions;

public class CannotSendEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CannotSendEmailException(String msg) {
		super(msg);
	}
	
	public CannotSendEmailException(Exception  ex) {
		super(ex);
	}

}
