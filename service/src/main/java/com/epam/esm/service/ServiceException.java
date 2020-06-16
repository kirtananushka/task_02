package com.epam.esm.service;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 5326133845772266856L;

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression,
	                        boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}