package com.epam.esm.service.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.SearchRepository;
import com.epam.esm.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchRepository searchRepository;

	@Override
	public Collection<Certificate> filter(String name,
	                                      String price,
	                                      String creationDate,
	                                      String modificationDate,
	                                      String duration) {
		return searchRepository.filter(name, price, creationDate, modificationDate, duration);
	}
}
