package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
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

	@GetMapping("/certificates/filter")
	public Collection<Certificate> filter(
					@RequestParam(value = "name", required = false, defaultValue = "") String name,
					@RequestParam(value = "price", required = false, defaultValue = "") String price,
					@RequestParam(value = "creation_date", required = false, defaultValue = "") String creationDate,
					@RequestParam(value = "modification_date", required = false, defaultValue = "") String modificationDate,
					@RequestParam(value = "duration", required = false, defaultValue = "") String duration) {
		return searchService.filter(name, price, creationDate, modificationDate, duration);
	}
}
