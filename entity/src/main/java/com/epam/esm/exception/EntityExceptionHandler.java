package com.epam.esm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(EntityOperationException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.NOT_FOUND.value(),
						HttpStatus.NOT_FOUND.toString(),
						e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(Exception e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.toString(),
						e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}