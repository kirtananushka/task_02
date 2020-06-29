package com.epam.esm.exception;

import com.epam.esm.repository.RepositoryNotFoundException;
import com.epam.esm.service.ServiceConflictRequestException;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.ServiceNotFoundException;
import com.epam.esm.util.UnexpectedValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * Class EntityExceptionHandler for task 2.
 *
 * @author KIR TANANUSHKA
 * @version 1.0
 */
@ControllerAdvice
public class EntityExceptionHandler {

	/**
	 * Method handleException.
	 *
	 * @param e of type ServiceException  .
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(ServiceException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type ServiceNotFoundException  .
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(ServiceNotFoundException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.NOT_FOUND.value(),
						e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type RepositoryNotFoundException  .
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(
					RepositoryNotFoundException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.NOT_FOUND.value(),
						e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type Exception  .
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(Exception e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						"Bad request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type HttpMediaTypeNotSupportedException.
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(HttpMediaTypeNotSupportedException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
						"Unsupported media type");
		return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type ServiceConflictRequestException.
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(ServiceConflictRequestException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.CONFLICT.value(),
						e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type UnexpectedValueException.
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(UnexpectedValueException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type HttpMessageNotReadableException.
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(HttpMessageNotReadableException e) {
		String message = null;
		if (Objects.nonNull(e.getMessage())) {
			String errorMessage = e.getMessage();
			if (errorMessage.contains("Unexpected duration value")) {
				message = "Invalid duration";
			} else if (errorMessage.contains("Unexpected ID")) {
				message = "Invalid ID";
			} else if (errorMessage.contains("Unrecognized token")
							|| errorMessage.contains("Unexpected character")) {
				message = errorMessage.split(":")[1].strip();
			}
		}
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						Objects.nonNull(message) ? message : "Unexpected value: " + e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type MethodArgumentTypeMismatchException.
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(
					MethodArgumentTypeMismatchException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						"Unexpected value");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type DataIntegrityViolationException.
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(DataIntegrityViolationException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						"Database exception");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type NullPointerException.
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(NullPointerException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						"Missing required elements");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Method requestHandlingNoHandlerFound.
	 *
	 * @param e of type NoHandlerFoundException.
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> requestHandlingNoHandlerFound(
					NoHandlerFoundException e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						"Bad request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Method handleException.
	 *
	 * @param e of type Throwable.
	 * @return ResponseEntity&lt;EntityErrorResponse&gt;.
	 */
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(Throwable e) {
		EntityErrorResponse error = new EntityErrorResponse(
						HttpStatus.BAD_REQUEST.value(),
						"Internal server error" + e);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}