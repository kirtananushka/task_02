package com.epam.esm.service;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {

	public static final String ERROR_NO_CERTIFICATE_WITH_ID =
					"No certificate with such ID: ";
	public static final String ERROR_INVALID_CERTIFICATE_ID =
					"Invalid certificate ID";
	public static final String ERROR_INVALID_TAG_ID =
					"Invalid tag ID";
	public static final String ERROR_MISSING_CERTIFICATE_NAME =
					"Missing required element: certificate name";
	public static final String ERROR_MISSING_TAG_NAME =
					"Missing required element: tag name";
	public static final String ERROR_MISSING_DESCRIPTION =
					"Missing required element: certificate description";
	public static final String ERROR_INCORRECT_CERTIFICATE_NAME_LENGTH =
					"Incorrect certificate name length; expected 1...64";
	public static final String ERROR_INCORRECT_TAG_NAME_LENGTH =
					"Incorrect tag name length; expected 1...64, found: ";
	public static final String ERROR_INCORRECT_DESCRIPTION_LENGTH =
					"Incorrect certificate description length; expected 1...64";
	public static final String ERROR_INVALID_PRICE =
					"Invalid certificate price: positive number like 12345678.90 or 12345678 (precision of "
									+ "8 and scale of 2 or 0) expected";
	public static final String ERROR_INVALID_PRICE_EXPR =
					"Invalid expression with price";
	public static final String ERROR_INVALID_DURATION =
					"Invalid certificate duration: positive number 1...8 digits expected";
	public static final String ERROR_INVALID_DURATION_EXPR =
					"Invalid expression with duration: ";
	public static final String ERROR_INVALID_TAG_ID_EXPR =
					"Invalid expression with tad ID: ";
	public static final String ERROR_CERTIFICATE_NOT_CREATED =
					"Certificate not created";
	public static final String ERROR_CERTIFICATE_NOT_UPDATED =
					"Certificate not updated";
	public static final String ERROR_CERTIFICATE_NOT_DELETED =
					"Certificate not deleted";
	public static final String ERROR_CERTIFICATE_CONVERSION =
					"Error while certificate entity<->Dto conversion";
	public static final String ERROR_TAG_ID_NAME_NOT_RELEVANT =
					"ID and tag name are not relevant; ID: ";
	public static final String EXPECTED_NAME =
					", expected name: ";
	public static final String ERROR_NAME =
					", actual name: ";
	public static final String ERROR_NO_TAG_WITH_ID =
					"No tag with such id: ";
	public static final String ERROR_TAG_NOT_CREATED =
					"Tag not created";
	public static final String ERROR_TAG_NOT_DELETED =
					"Tag not deleted";
	public static final String ERROR_TAG_CONVERSION =
					"Error while tag entity<->Dto conversion";
	public static final String ERROR_CREATION_DATE_EXPR =
					"Invalid expression with creation date: ";
	public static final String ERROR_MODIFICATION_DATE_EXPR =
					"Invalid expression with modification date: ";
	public static final String ERROR_INVALID_PAGE =
					"Invalid page number: ";
	public static final String ERROR_INVALID_SIZE_PER_PAGE =
					"Invalid number of positions per page: ";
	public static final String ERROR_OVERSIZED_PAGE =
					"The number of positions per page is limited to ";
	public static final String ERROR_INCORRECT_PARAM_SORTING =
					"Incorrect parameter for sorting: ";
	public static final String ERROR_ADD_TAG_WITHOUT_NAME =
					"You cannot add new tag without name; tag ID: ";
	public static final String ERROR_CONFLICT_CERTIFICATE_ID_EXISTS =
					"Certificate with the specified ID already exists: ";
	public static final String ERROR_CONFLICT_CERTIFICATE_ID_NEW =
					"You cannot assign an identifier to a new certificate; identifiers are assigned automatically";
	public static final String ERROR_CONFLICT_TAG_ID_EXISTS =
					"Tag with the specified ID already exists: ";
	public static final String ERROR_CONFLICT_TAG_ID_NEW =
					"You cannot assign an identifier to a new tag; identifiers are assigned automatically";
}
