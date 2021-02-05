package com.gaepom.exception;

public class PortfolioException extends RuntimeException {
	public PortfolioException() {}
	
	public PortfolioException(String message) {
		super(message);
	}
}