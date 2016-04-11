package com.epam.exception;

public class ElementNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4180056510818501003L;

	public ElementNotFoundException(String message) {
		super(message);
	}

	public ElementNotFoundException(String message, Throwable ex){
		super(message, ex);
	}
}
