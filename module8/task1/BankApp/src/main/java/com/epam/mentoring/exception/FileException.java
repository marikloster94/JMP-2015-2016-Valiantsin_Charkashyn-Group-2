package com.epam.mentoring.exception;

public class FileException extends Exception {

	
	private static final long serialVersionUID = -1409601674994371940L;
	
	public FileException(String message){
		super(message);
	}
	
	public FileException(String message, Throwable ex){
		super(message, ex);
	}

}
