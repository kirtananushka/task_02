package com.epam.esm.entity;

import com.epam.esm.util.LocalDateDeserializer;
import com.epam.esm.util.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class Certificate implements Serializable {

	private static final long serialVersionUID = -2426966202789827763L;
	private long id;
	private String name;
	private String description;
	private BigDecimal price;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate creationDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate modificationDate;
	private int duration;
	private List<Tag> tags;
}
