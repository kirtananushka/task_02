package com.epam.esm.util;

public class UnexpectedValueException extends RuntimeException {

	private static final long serialVersionUID = 8383421975017981936L;

	public UnexpectedValueException() {
	}

	public UnexpectedValueException(String message) {
		super(message);
	}

	public UnexpectedValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedValueException(Throwable cause) {
		super(cause);
	}

	public UnexpectedValueException(String message, Throwable cause, boolean enableSuppression,
	                                boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
