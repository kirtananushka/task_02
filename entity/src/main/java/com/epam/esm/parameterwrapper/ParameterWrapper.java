package com.epam.esm.parameterwrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ParameterWrapper {

	private String name;
	private String description;
	private String price;
	private String creationDate;
	private String modificationDate;
	private String duration;
	private String tag;
	private String sortBy;
	private String perPage;
	private String page;
}
