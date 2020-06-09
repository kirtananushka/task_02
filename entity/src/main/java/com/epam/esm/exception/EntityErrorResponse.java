package com.epam.esm.exception;

public class EntityErrorResponse {

	private int status;
	private String message;

	public EntityErrorResponse() {
	}

	public EntityErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
