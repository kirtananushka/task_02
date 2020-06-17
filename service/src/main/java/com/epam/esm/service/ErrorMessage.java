package com.epam.esm.service;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {

	public static final String ERROR_NO_CERTIFICATE_WITH_ID =
					"No certificate with such ID: ";
	public static final String ERROR_INVALID_CERTIFICATE_ID =
					"Invalid certificate ID: ";
	public static final String ERROR_INVALID_TAG_ID =
					"Invalid tag ID: ";
	public static final String ERROR_INCORRECT_CERTIFICATE_NAME_LENGTH =
					"Incorrect certificate name length; expected 1...64, found: ";
	public static final String ERROR_INCORRECT_TAG_NAME_LENGTH =
					"Incorrect tag name length; expected 1...64, found: ";
	public static final String ERROR_INCORRECT_DESCRIPTION_LENGTH =
					"Incorrect certificate description length; expected 1...64, found: ";
	public static final String ERROR_INVALID_PRICE =
					"Invalid certificate price: ";
	public static final String ERROR_INVALID_PRICE_EXPR =
					"Invalid expression with price: ";
	public static final String ERROR_INVALID_DURATION =
					"Invalid certificate duration: ";
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
					"Error while certificate entity<->DTO conversion";
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
					"Error while tag entity<->DTO conversion";
	public static final String ERROR_CREATION_DATE_EXPR =
					"Invalid expression with creation date: ";
	public static final String ERROR_MODIFICATION_DATE_EXPR =
					"Invalid expression with modification date: ";
	public static final String ERROR_INVALID_PAGE =
					"Invalid page number: ";
	public static final String ERROR_INVALID_PER_PAGE =
					"Invalid number of positions per page: ";
	public static final String ERROR_INCORRECT_PARAM_SORTING =
					"Incorrect parameter for sorting: ";
	public static final String ERROR_ADD_TAG_WITHOUT_NAME =
					"You cannot add new tag without name; tag ID: ";
}
