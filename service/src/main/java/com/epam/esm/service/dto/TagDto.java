package com.epam.esm.service.dto;

import com.epam.esm.service.ErrorMessage;
import com.epam.esm.util.LongDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Component
@Data
@EqualsAndHashCode(callSuper = true)
public class TagDto extends EntityDto {

	@JsonDeserialize(using = LongDeserializer.class)
	@NotNull(message = ErrorMessage.ERROR_INVALID_TAG_ID)
	@PositiveOrZero(message = ErrorMessage.ERROR_INVALID_TAG_ID)
	private Long id;
	@Size(min = 1, max = 64, message = ErrorMessage.ERROR_INCORRECT_TAG_NAME_LENGTH)
	private String name;
}
