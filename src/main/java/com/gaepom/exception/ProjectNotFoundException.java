package com.gaepom.exception;

public class ProjectNotFoundException extends ProjectException {
	public ProjectNotFoundException() {}
	
	public ProjectNotFoundException(String message) {
		super(message);
	}
}
