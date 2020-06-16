package com.epam.esm.service.validation;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Component
public class Validator {

	private static final char DECIMAL_SEPARATOR = '.';
	private static final String MONEY_DECIMAL_FORMAT = "#0.00";
	private static final String DECIMAL_PATTERN = "\\d+.?\\d*";
	private static final String INTEGER_PATTERN = "\\d+";
	private static final String DATE_PATTERN = "\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d";
	private static final String SORTING_PATTERN = "[\\-]?[a-z_.]+";
	private static final String MINUS = "\\-";
	private static final String GREATER_OR_LESS = "[\\<\\>]";
	private static final String GREATER_LESS = "[\\<\\>\\!][\\>\\=]";
	private static final String BETWEEN = "BETWEEN ";
	private static final String NOT = "NOT ";
	private static final String AND = " AND ";
	private static final List<String> sortParams = Arrays.asList("certificates.id", "name",
					"description", "price", "creation_date", "modification_date", "duration");

	public static boolean checkLong(Long longNumber) {
		return longNumber >= 0;
	}

	public static boolean checkPositiveLong(Long longNumber) {
		return longNumber > 0;
	}

	public static boolean checkInt(int duration) {
		return duration > 0;
	}

	public static boolean checkText(String text) {
		return !(text.length() == 0 || text.length() >= 64);
	}

	public static boolean checkPrice(String price) {
		if (!price.matches(DECIMAL_PATTERN)) {
			return false;
		}
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(DECIMAL_SEPARATOR);
		DecimalFormat decimalFormat = new DecimalFormat(MONEY_DECIMAL_FORMAT, symbols);
		decimalFormat.setParseBigDecimal(true);
		try {
			decimalFormat.parse(price);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static boolean checkPriceQuery(String price) {
		return checkQuery(price, DECIMAL_PATTERN);
	}

	public static boolean checkDateQuery(String date) {
		return checkQuery(date, DATE_PATTERN);
	}

	public static boolean checkIntegerQuery(String duration) {
		return checkStrInteger(duration) &&
						checkQuery(duration, INTEGER_PATTERN);
	}

	public static boolean checkSortBy(String param) {
		param = param.toLowerCase().strip();
		if (!param.matches(SORTING_PATTERN)) {
			return false;
		}
		param = param.replaceAll(MINUS, "");
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

