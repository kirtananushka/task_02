package com.epam.esm.service.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TagDTO {

	private Long id;
	private String name;
}
