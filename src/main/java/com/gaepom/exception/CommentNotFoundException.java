package com.gaepom.exception;

public class CommentNotFoundException extends CommentException {
	public CommentNotFoundException() {}
	public CommentNotFoundException(String message) {
		super(message);
	}
}
