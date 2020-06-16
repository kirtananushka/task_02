package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
import com.epam.esm.parameterwrapper.ParameterWrapper;
import com.epam.esm.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/certificates")
	public Collection<Certificate> query(
					@RequestParam(value = "name", required = false) String name,
					@RequestParam(value = "description", required = false) String description,
					@RequestParam(value = "price", required = false) String price,
					@RequestParam(value = "creation_date", required = false) String creationDate,
					@RequestParam(value = "modification_date", required = false) String modificationDate,
					@RequestParam(value = "duration", required = false) String duration,
					@RequestParam(value = "tag", required = false) String tag,
					@RequestParam(value = "sort", required = false, defaultValue = "certificates.id") String sortBy,
					@RequestParam(value = "per_page", required = false, defaultValue = "10") String perPage,
					@RequestParam(value = "page", required = false) String page) {
		ParameterWrapper params = new ParameterWrapper();
		params.setName(name);
		params.setDescription(description);
		params.setPrice(price);
		params.setCreationDate(creationDate);
		params.setModificationDate(modificationDate);
		params.setDuration(duration);
		params.setTag(tag);
		params.setSortBy(sortBy);
		params.setPerPage(perPage);
		params.setPage(page);
		return searchService.search(params);
	}
}
