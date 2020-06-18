package com.epam.esm.service.dto;

import com.epam.esm.service.ErrorMessage;
import com.epam.esm.util.LocalDateDeserializer;
import com.epam.esm.util.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CertificateDTO extends EntityDTO {

	@NotNull(message = ErrorMessage.ERROR_INVALID_CERTIFICATE_ID)
	@PositiveOrZero(message = ErrorMessage.ERROR_INVALID_CERTIFICATE_ID)
	private Long id;
	@NotNull(message = ErrorMessage.ERROR_MISSING_CERTIFICATE_NAME)
	@Size(min = 1, max = 64, message = ErrorMessage.ERROR_INCORRECT_CERTIFICATE_NAME_LENGTH)
	private String name;
	@NotNull(message = ErrorMessage.ERROR_MISSING_DESCRIPTION)
	@Size(min = 1, max = 64, message = ErrorMessage.ERROR_INCORRECT_DESCRIPTION_LENGTH)
	private String description;
	@PositiveOrZero(message = ErrorMessage.ERROR_INVALID_PRICE)
	@Digits(integer = 8, fraction = 2, message = ErrorMessage.ERROR_INVALID_PRICE)
	private BigDecimal price;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate creationDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate modificationDate;
	@Positive(message = ErrorMessage.ERROR_INVALID_DURATION)
	@Digits(integer = 8, fraction = 2, message = ErrorMessage.ERROR_INVALID_DURATION)
	private int duration;
	private List<TagDTO> tags;
}
