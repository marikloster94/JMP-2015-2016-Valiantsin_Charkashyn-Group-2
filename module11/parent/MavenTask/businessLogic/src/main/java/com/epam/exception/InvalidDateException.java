package com.epam.exception;

public class InvalidDateException extends Exception {
	
	private static final long serialVersionUID = -4210887807764618005L;

	public InvalidDateException(String message){
		super(message);
	}
	
	public InvalidDateException(String message, Throwable ex){
		super(message, ex);
	}
}
