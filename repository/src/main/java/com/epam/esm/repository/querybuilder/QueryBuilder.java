package com.epam.esm.repository.querybuilder;

import com.epam.esm.parameterwrapper.ParameterWrapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class QueryBuilder {

	public static final String SELECT_CERTIFICATES = "SELECT DISTINCT "
					+ "certificates.id, certificates.name, description, price, creation_date, "
					+ "modification_date, duration FROM certificates LEFT JOIN (tags INNER JOIN "
					+ "certificate_tag ON tags.id = tag_id) ON  certificates.id = certificate_id ";
	public static final String SELECT_TAGS = "SELECT DISTINCT "
					+ "tags.id, tags.name FROM tags ORDER BY id ";
	public static final String CLAUSE_TSQUERY = "');";
	public static final String CLAUSE_STRING = "';";
	public static final String SEMICOLON = ";";
	public static final String SPACE = " ";
	public static final String COMMA = ",";
	public static final String EMPTINESS = "";
	public static final String MINUS = "-";
	public static final String NOT = "NOT";
	public static final String PATTERN_SORT = "[,-]";
	public static final String PATTERN_DATE = "[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])";
	public static final String PATTERN_DIGIT = "\\d+";
	public static final String NAME = "WHERE to_tsvector(certificates.name) @@ plainto_tsquery('";
	public static final String DESCRIPTION = "WHERE to_tsvector(description) @@ plainto_tsquery('";
	public static final String PRICE = "WHERE price=";
	public static final String CREATION_DATE = "creation_date";
	public static final String MODIFICATION_DATE = "modification_date";
	public static final String DURATION = "WHERE duration=";
	public static final String TAG = "WHERE tags.name='";
	public static final String TAG_ID = "WHERE tags.id=";
	public static final String ORDER = "ORDER BY ";
	public static final String DESC = " DESC;";
	public static final String RE_WHERE = ";WHERE ";
	public static final String RE_ORDER_BY = ";ORDER BY";
	public static final String RE_ORDER = "; ORDER";
	public static final String RE_LIMIT = "; LIMIT";
	public static final String RE_GREATER = "=>";
	public static final String RE_GREATER_STR = "='>";
	public static final String RE_GREATER_EQUAL_STR = "='>=";
	public static final String RE_LESS_EQUAL_STR = "='<=";
	public static final String RE_LESS = "=<";
	public static final String RE_LESS_STR = "=<'";
	public static final String RE_GREATER_LESS = "=<>";
	public static final String RE_GREATER_LESS_STR = "='<>";
	public static final String RE_NOT_EQUAL = "=!";
	public static final String RE_NOT_EQUAL_STR = "='!=";
	public static final String RE_BETWEEN = "=BETWEEN";
	public static final String RE_NOT_BETWEEN = "=NOT BETWEEN";
	public static final String RP_AND = " AND ";
	public static final String RP_ORDER = " ORDER";
	public static final String RP_LIMIT = " LIMIT";
	public static final String RP_GREATER = ">";
	public static final String RP_GREATER_STR = ">'";
	public static final String RP_GREATER_EQUAL_STR = ">='";
	public static final String RP_LESS_EQUAL_STR = "<='";
	public static final String RP_LESS = "<";
	public static final String RP_LESS_STR = "<'";
	public static final String RP_GREATER_LESS = "<>";
	public static final String RP_GREATER_LESS_STR = "<>'";
	public static final String RP_NOT_EQUAL = "!";
	public static final String RP_NOT_EQUAL_STR = "!='";
	public static final String RP_BETWEEN = " BETWEEN";
	public static final String RP_NOT_BETWEEN = " NOT BETWEEN";
	public static final String T_WHERE = "WHERE ";
	public static final String TC_BETWEEN = "BETWEEN";
	public static final String T_NOT_BETWEEN = " NOT BETWEEN '";
	public static final String T_BETWEEN = " BETWEEN '";
	public static final String T_AND = "' AND '";
	public static final String T_EQUALS = "='";
	public static final String LIMIT = " LIMIT ";
	public static final String OFFSET = " OFFSET ";

	public StringBuilder buildCertificateColumns(ParameterWrapper params) {
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT_CERTIFICATES);
		if (Objects.nonNull(params.getName())) {
			builder.append(NAME)
			       .append(params.getName())
			       .append(CLAUSE_TSQUERY);
		}
		if (Objects.nonNull(params.getDescription())) {
			builder.append(DESCRIPTION)
			       .append(params.getDescription())
			       .append(CLAUSE_TSQUERY);
		}
		if (Objects.nonNull(params.getPrice())) {
			builder.append(PRICE)
			       .append(params.getPrice().toUpperCase())
			       .append(SEMICOLON);
		}
		if (Objects.nonNull(params.getCreationDate())) {
			builder.append(buildDate(params.getCreationDate(), CREATION_DATE));
		}
		if (Objects.nonNull(params.getModificationDate())) {
			builder.append(buildDate(params.getModificationDate(), MODIFICATION_DATE));
		}
		if (Objects.nonNull(params.getDuration())) {
			builder.append(DURATION)
			       .append(params.getDuration().toUpperCase())
			       .append(SEMICOLON);
		}
		if (Objects.nonNull(params.getTagId())) {
			builder.append(TAG_ID)
			       .append(params.getTagId().toUpperCase())
			       .append(SEMICOLON);
		}
		if (Objects.nonNull(params.getTagName())) {
			builder.append(TAG)
			       .append(params.getTagName())
			       .append(CLAUSE_STRING);
		}
		return builder;
	}

	public StringBuilder buildTagColumns(ParameterWrapper params) {
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT_TAGS);
		return builder;
	}

	public StringBuilder buildSorting(ParameterWrapper params) {
		Pattern pattern = Pattern.compile(PATTERN_SORT);
		Matcher matcher = pattern.matcher(params.getSortBy());
		StringBuilder builder = new StringBuilder().append(SPACE);
		if (!matcher.find()) {
			builder.append(ORDER)
			       .append(params.getSortBy())
			       .append(SEMICOLON);
		} else {
			String[] sortExpr = params.getSortBy().split(COMMA);
			for (String element : sortExpr) {
				if (element.startsWith(MINUS)) {
					element = element.replaceAll(MINUS, EMPTINESS);
					builder.append(ORDER)
					       .append(element)
					       .append(DESC);
				} else {
					builder.append(ORDER)
					       .append(element)
					       .append(SEMICOLON);
				}
			}
		}
		return builder;
	}

	public String makeReplacement(StringBuilder builder) {
		return builder.toString()
		              .replaceAll(RE_WHERE, RP_AND)
		              .replaceAll(RE_ORDER_BY, COMMA)
		              .replaceAll(RE_ORDER, RP_ORDER)
		              .replaceAll(RE_LIMIT, RP_LIMIT)
		              .replaceAll(RE_GREATER_EQUAL_STR, RP_GREATER_EQUAL_STR)
		              .replaceAll(RE_GREATER_STR, RP_GREATER_STR)
		              .replaceAll(RE_GREATER, RP_GREATER)
		              .replaceAll(RE_LESS_EQUAL_STR, RP_LESS_EQUAL_STR)
		              .replaceAll(RE_LESS_STR, RP_LESS_STR)
		              .replaceAll(RE_LESS, RP_LESS)
		              .replaceAll(RE_GREATER_LESS, RP_GREATER_LESS)
		              .replaceAll(RE_GREATER_LESS_STR, RP_GREATER_LESS_STR)
		              .replaceAll(RE_BETWEEN, RP_BETWEEN)
		              .replaceAll(RE_NOT_BETWEEN, RP_NOT_BETWEEN)
		              .replaceAll(RE_NOT_EQUAL_STR, RP_NOT_EQUAL_STR)
		              .replaceAll(RE_NOT_EQUAL, RP_NOT_EQUAL);
	}

	public StringBuilder buildPagination(ParameterWrapper params) {
		StringBuilder builder = new StringBuilder();
		if (Objects.nonNull(params.getPage())) {
			String strPerPage = params.getSize();
			int perPage = 0;
			if (strPerPage.matches(PATTERN_DIGIT)) {
				perPage = Integer.parseInt(strPerPage);
			}
			String strPage = params.getPage();
			int page = 0;
			if (strPage.matches(PATTERN_DIGIT)) {
				page = Integer.parseInt(strPage);
			}
			int offset = perPage * (page - 1);
			builder.append(LIMIT)
			       .append(perPage)
			       .append(OFFSET)
			       .append(offset)
			       .append(SEMICOLON);
		}
		return builder;
	}

	private StringBuilder buildDate(String param, String columnName) {
		StringBuilder builder = new StringBuilder().append(T_WHERE)
		                                           .append(columnName);
		if (param.toUpperCase().contains(TC_BETWEEN)) {
			Pattern pattern = Pattern.compile(PATTERN_DATE);
			Matcher matcher = pattern.matcher(param);
			if (param.toUpperCase().contains(NOT)) {
				builder.append(T_NOT_BETWEEN);
			} else {
				builder.append(T_BETWEEN);
			}
			matcher.find();
			builder.append(matcher.group()).append(T_AND);
			matcher.find();
			builder.append(matcher.group());
		} else {
			builder.append(T_EQUALS)
			       .append(param);
		}
		return builder.append(CLAUSE_STRING);
	}
}
