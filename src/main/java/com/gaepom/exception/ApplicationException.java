package com.gaepom.exception;

public class ApplicationException extends RuntimeException {
	public ApplicationException() {}
	
	public ApplicationException(String message) {
		super(message);
	}
}