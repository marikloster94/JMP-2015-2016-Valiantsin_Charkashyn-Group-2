package com.epam.exception;

public class AddNewElementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1943614629913757572L;

	public AddNewElementException(String message){
		super(message);
	}
	
	public AddNewElementException(String message, Throwable ex){
		super(message, ex);
	}
}
