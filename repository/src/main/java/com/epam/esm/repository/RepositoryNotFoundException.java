package com.epam.esm.repository;

public class RepositoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5755086438932353401L;

	public RepositoryNotFoundException() {
	}

	public RepositoryNotFoundException(String message) {
		super(message);
	}

	public RepositoryNotFoundException(Throwable cause) {
		super(cause);
	}

	public RepositoryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryNotFoundException(String message, Throwable cause, boolean enableSuppression,
	                                   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}