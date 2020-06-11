package com.epam.esm.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tag implements Serializable {

	private static final long serialVersionUID = -4486690540247689306L;
	private long id;
	private String name;
}
