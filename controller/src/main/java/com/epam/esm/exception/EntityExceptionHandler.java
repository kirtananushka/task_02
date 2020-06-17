package com.epam.esm.exception;

import com.epam.esm.service.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class EntityExceptionHandler {

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<EntityErrorResponse> handleException(ServiceException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.toString(),
						e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(Exception e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.toString(),
						"Bad request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(HttpMessageNotReadableException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.toString(),
						"Unexpected value");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(
					MethodArgumentTypeMismatchException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.toString(),
						"Unexpected value");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(DataIntegrityViolationException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.toString(),
						"Database exception");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(NullPointerException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.toString(),
						"No object");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> requestHandlingNoHandlerFound(
					NoHandlerFoundException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.toString(),
						"Bad request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(Throwable e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.toString(),
						"Internal server error" + e);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}