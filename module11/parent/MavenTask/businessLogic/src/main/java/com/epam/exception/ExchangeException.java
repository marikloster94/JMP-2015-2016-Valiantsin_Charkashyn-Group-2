package com.epam.exception;

public class ExchangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9172899729649297954L;
	
	public ExchangeException(String message){
		super(message);
	}
	
	public ExchangeException(String message, Throwable ex){
		super(message, ex);
	}
}
