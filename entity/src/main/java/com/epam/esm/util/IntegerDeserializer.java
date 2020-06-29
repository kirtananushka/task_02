package com.epam.esm.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class IntegerDeserializer extends StdDeserializer<Integer> {

	protected IntegerDeserializer() {
		super(Integer.class);
	}

	@Override
	public Integer deserialize(JsonParser jsonParser, DeserializationContext ctxt) {
		int parsedInt;
		try {
			parsedInt = Integer.parseInt(jsonParser.readValueAs(String.class));
		} catch (Exception e) {
			throw new UnexpectedValueException("Unexpected duration value");
		}
		return parsedInt;
	}
}
