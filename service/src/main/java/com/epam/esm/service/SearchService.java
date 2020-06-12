package com.epam.esm.service;

import com.epam.esm.entity.Certificate;

import java.util.Collection;

public interface SearchService {

	Collection<Certificate> filter(String name, String price, String creationDate,
	                               String modificationDate, String duration);
}
