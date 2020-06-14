package com.epam.esm.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

	private static final Long serialVersionUID = 8382822642446032421L;

	public LocalDateSerializer() {
		super(LocalDate.class);
	}

	@Override
	public void serialize(LocalDate localDate, JsonGenerator jsonGenerator,
	                      SerializerProvider serializerProvider) throws
					IOException {
		jsonGenerator.writeString(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
	}
}