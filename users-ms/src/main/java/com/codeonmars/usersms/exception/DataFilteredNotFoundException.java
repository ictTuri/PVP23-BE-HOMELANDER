package com.codeonmars.usersms.exception;

public class DataFilteredNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataFilteredNotFoundException(String message) {
		super(message);
	}
}
