package com.epam.esm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class EntityErrorResponse for task 2.
 *
 * @author KIR TANANUSHKA
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntityErrorResponse {

	private int status;
	private String error;
	private String message;
}
