package com.epam.esm.parameterwrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParameterWrapper {

	private String name;
	private String description;
	private String price;
	private String creationDate;
	private String modificationDate;
	private String duration;
	private String tagName;
	private String tagId;
	private String sortBy;
	private String size;
	private String page;
}
