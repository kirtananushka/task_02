package com.epam.esm.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LongDeserializer extends StdDeserializer<Long> {

	protected LongDeserializer() {
		super(Long.class);
	}

	@Override
	public Long deserialize(JsonParser jsonParser, DeserializationContext ctxt) {
		Long parsedLong;
		try {
			parsedLong = Long.parseLong(jsonParser.readValueAs(String.class));
		} catch (Exception e) {
			throw new UnexpectedValueException("Unexpected ID");
		}
		return parsedLong;
	}
}