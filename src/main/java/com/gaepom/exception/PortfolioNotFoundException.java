package com.gaepom.exception;

public class PortfolioNotFoundException extends RuntimeException {
	public PortfolioNotFoundException() {}
	
	public PortfolioNotFoundException(String message) {
		super(message);
	}
}