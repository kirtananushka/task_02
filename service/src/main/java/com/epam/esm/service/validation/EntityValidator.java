package com.epam.esm.service.validation;

import com.epam.esm.service.ServiceException;
import com.epam.esm.service.dto.EntityDTO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class EntityValidator {

	private static final String EMPTINESS = "";
	private static final String DECIMAL_PATTERN = "\\d{1,8}\\.\\d\\d";
	private static final String INTEGER_PATTERN = "\\d{1,8}";
	private static final String NON_DIGITS_PATTERN = "\\D";
	private static final String DATE_PATTERN = "\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d";
	private static final String SORTING_PATTERN = "[\\-]?[a-z_.]+";
	private static final String MINUS = "\\-";
	private static final String GREATER_OR_LESS = "[\\<\\>]";
	private static final String GREATER_LESS = "[\\<\\>\\!][\\>\\=]";
	private static final String BETWEEN = "BETWEEN ";
	private static final String NOT = "NOT ";
	private static final String AND = " AND ";
	private static final String SEMICOLON = "; ";
	private static final List<String> sortParams = Arrays.asList("certificates.id", "name",
					"description", "price", "creation_date", "modification_date", "duration");

	public static <T extends EntityDTO> void validate(T entityDTO) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(entityDTO);
		if (Objects.nonNull(constraintViolations) && !constraintViolations.isEmpty()) {
			StringBuilder errorBuilder = new StringBuilder();
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				errorBuilder.append(SEMICOLON)
				            .append(constraintViolation.getMessage());
			}
			String errorMessage = errorBuilder.toString().substring(2);
			throw new ServiceException(errorMessage);
		}
	}

	public static boolean checkLong(Long longNumber) {
		return longNumber >= 0;
	}

	public static boolean checkText(String text) {
		return (text.length() > 0 && text.length() <= 64);
	}

	public static boolean checkPriceQuery(String price) {
		return checkQuery(price, DECIMAL_PATTERN) || checkQuery(price, INTEGER_PATTERN);
	}

	public static boolean checkDateQuery(String date) {
		return checkQuery(date, DATE_PATTERN);
	}

	public static boolean checkIntegerQuery(String intExpr) {
		String digitals = intExpr.replaceAll(NON_DIGITS_PATTERN, EMPTINESS);
		return checkStrInteger(digitals) &&
						checkQuery(intExpr, INTEGER_PATTERN);
	}

	public static boolean checkSortBy(String param) {
		param = param.toLowerCase().strip();
		if (!param.matches(SORTING_PATTERN)) {
			return false;
		}
		param = param.replaceAll(MINUS, EMPTINESS);
		return sortParams.contains(param);
	}

	private static boolean checkQuery(String query, String pattern) {
		query = query.toUpperCase();
		return query.matches(pattern) ||
						query.matches(GREATER_OR_LESS + pattern) ||
						query.matches(GREATER_LESS + pattern) ||
						query.matches(BETWEEN + pattern + AND + pattern) ||
						query.matches(NOT + BETWEEN + pattern + AND + pattern);
	}

	private static boolean checkStrInteger(String strInteger) {
		if (!NumberUtils.isCreatable(strInteger)) {
			return false;
		}
		Integer number;
		try {
			number = NumberUtils.createInteger(strInteger);
		} catch (NumberFormatException e) {
			return false;
		}
		return number >= 0;
	}
}

