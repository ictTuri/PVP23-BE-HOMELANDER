package com.codeonmars.usersms.exception;

public class DataIdNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public DataIdNotFoundException(String message) {
		super(message);
	}
}
