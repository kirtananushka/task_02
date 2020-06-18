package com.epam.esm.service;

public class ServiceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9133928679322407067L;

	public ServiceNotFoundException() {
	}

	public ServiceNotFoundException(String message) {
		super(message);
	}

	public ServiceNotFoundException(Throwable cause) {
		super(cause);
	}

	public ServiceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceNotFoundException(String message, Throwable cause, boolean enableSuppression,
	                                boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}