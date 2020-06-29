package com.epam.esm.service;

public class ServiceConflictRequestException extends RuntimeException {

	private static final long serialVersionUID = -9133928679322407067L;

	public ServiceConflictRequestException() {
	}

	public ServiceConflictRequestException(String message) {
		super(message);
	}

	public ServiceConflictRequestException(Throwable cause) {
		super(cause);
	}

	public ServiceConflictRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceConflictRequestException(String message, Throwable cause, boolean enableSuppression,
	                                       boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}