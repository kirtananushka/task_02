package com.epam.esm.exception;

public class EntityOperationException extends RuntimeException {

	public EntityOperationException() {
	}

	public EntityOperationException(String message) {
		super(message);
	}

	public EntityOperationException(Throwable cause) {
		super(cause);
	}

	public EntityOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityOperationException(String message, Throwable cause, boolean enableSuppression,
	                                boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}