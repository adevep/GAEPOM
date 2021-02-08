package com.gaepom.exception;

public class PortfolioNotFoundException extends PortfolioException {
	public PortfolioNotFoundException() {}
	
	public PortfolioNotFoundException(String message) {
		super(message);
	}
}
