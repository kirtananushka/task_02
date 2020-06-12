package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;

import java.util.Collection;

public interface SearchRepository {

	Collection<Certificate> filter(String name, String price, String creationDate,
	                               String modificationDate, String duration);
}
