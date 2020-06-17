package com.epam.esm.controller;

import com.epam.esm.parameterwrapper.ParameterWrapper;
import com.epam.esm.service.SearchService;
import com.epam.esm.service.dto.CertificateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Class SearchController for task 2.
 *
 * @author KIR TANANUSHKA
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificates")
public class SearchController {

	/** Field searchService  */
	private final SearchService searchService;

	/**
	 * GET method query returns a collection of filtered
	 * and/or sorted CertificateDTO objects.
	 *
	 * Filtering by name:
	 * [GET /api/v1/certificates?name={name}]
	 *
	 * Filtering by description:
	 * [GET /api/v1/certificates?description={description}]
	 *
	 * Filtering by price:
	 * [GET /api/v1/certificates?price={price}]
	 * [GET /api/v1/certificates?price=&gt;{price}]
	 * [GET /api/v1/certificates?price=&lt;{price}]
	 * [GET /api/v1/certificates?price=&lt;&gt;{price}]
	 * [GET /api/v1/certificates?price=!={price}]
	 * [GET /api/v1/certificates?price=between {price} and {price}]
	 * [GET /api/v1/certificates?price=not between {price} and {price}]
	 *
	 * Filtering by creation date:
	 * [GET /api/v1/certificates?creation_date={creation_date}]
	 * [GET /api/v1/certificates?creation_date=&gt;{creation_date}]
	 * [GET /api/v1/certificates?creation_date=&lt;{creation_date}]
	 * [GET /api/v1/certificates?creation_date=&lt;&gt;{creation_date}]
	 * [GET /api/v1/certificates?creation_date=!={creation_date}]
	 * [GET /api/v1/certificates?creation_date=between {creation_date} and {creation_date}]
	 * [GET /api/v1/certificates?creation_date=not between {creation_date} and {creation_date}]
	 *
	 * Filtering by modification date:
	 * [GET /api/v1/certificates?modification_date={modification_date}]
	 * [GET /api/v1/certificates?modification_date=&gt;{modification_date}]
	 * [GET /api/v1/certificates?modification_date=&lt;{modification_date}]
	 * [GET /api/v1/certificates?modification_date=&lt;&gt;{modification_date}]
	 * [GET /api/v1/certificates?modification_date=!={modification_date}]
	 * [GET /api/v1/certificates?modification_date=between {modification_date} and {modification_date}]
	 * [GET /api/v1/certificates?modification_date=not between {modification_date} and {modification_date}]
	 *
	 * Filtering by duration:
	 * [GET /api/v1/certificates?duration={duration}]
	 * [GET /api/v1/certificates?duration=&gt;{duration}]
	 * [GET /api/v1/certificates?duration=&lt;{duration}]
	 * [GET /api/v1/certificates?duration=&lt;&gt;{duration}]
	 * [GET /api/v1/certificates?duration=!={duration}]
	 * [GET /api/v1/certificates?duration=between {duration} and {duration}]
	 * [GET /api/v1/certificates?duration=not between {duration} and {duration}]
	 *
	 * Filtering by tad ID:
	 * [GET /api/v1/certificates?tag_id={tag_id}]
	 * [GET /api/v1/certificates?tag_id=&gt;{tag_id}]
	 * [GET /api/v1/certificates?tag_id=&lt;{tag_id}]
	 * [GET /api/v1/certificates?tag_id=&lt;&gt;{tag_id}]
	 * [GET /api/v1/certificates?tag_id=!={tag_id}]
	 * [GET /api/v1/certificates?tag_id=between {tag_id} and {tag_id}]
	 * [GET /api/v1/certificates?tag_id=not between {tag_id} and {tag_id}]
	 *
	 * Filtering by tag name:
	 * [GET /api/v1/certificates?tag_name={tag_name}]
	 *
	 * Filtering by multiple parameters:
	 * [GET /api/v1/certificates?name={name}&amp;description={description}] etc.
	 *
	 * Sorting in ascending order by certificate ID:
	 * [GET /api/v1/certificates?sort=certificates.id]
	 *
	 * Sorting in descending order by certificate ID:
	 * [GET /api/v1/certificates?sort=-certificates.id]
	 *
	 * Sorting in ascending order by certificate name:
	 * [GET /api/v1/certificates?sort=name]
	 *
	 * Sorting in descending order by certificate name:
	 * [GET /api/v1/certificates?sort=-name]
	 *
	 * Sorting in ascending order by description:
	 * [GET /api/v1/certificates?sort=description]
	 *
	 * Sorting in descending order by description:
	 * [GET /api/v1/certificates?sort=-description]
	 *
	 * Sorting in ascending order by price:
	 * [GET /api/v1/certificates?sort=price]
	 *
	 * Sorting in descending order by price:
	 * [GET /api/v1/certificates?sort=-price]
	 *
	 * Sorting in ascending order by creation date:
	 * [GET /api/v1/certificates?sort=creation_date]
	 *
	 * Sorting in descending order by creation date:
	 * [GET /api/v1/certificates?sort=-creation_date]
	 *
	 * Sorting in ascending order by modification date:
	 * [GET /api/v1/certificates?sort=modification_date]
	 *
	 * Sorting in descending order by modification date:
	 * [GET /api/v1/certificates?sort=-modification_date]
	 *
	 * Sorting in ascending order by duration:
	 * [GET /api/v1/certificates?sort=duration]
	 *
	 * Sorting in descending order by duration:
	 * [GET /api/v1/certificates?sort=-duration]
	 *
	 * Sorting by multiple parameters:
	 * [GET /api/v1/certificates?sort=-name,duration,-price] etc.
	 *
	 * Filtering and sorting by multiple parameters:
	 * [GET /api/v1/certificates?name={name}&amp;sort=-name,duration,-price] etc.
	 *
	 * Pagination (by default, 10 entries per page if a page number is specified):
	 * [GET /api/v1/certificates?page=1&amp;per_page=10] etc.
	 *
	 * Request (application/json).
	 * Response 200 (application/json).
	 *
	 * @param name of type String.
	 * @param description of type String.
	 * @param price of type String.
	 * @param creationDate of type String.
	 * @param modificationDate of type String.
	 * @param duration of type String.
	 * @param tagName of type String.
	 * @param tagId of type String.
	 * @param sortBy of type String.
	 * @param perPage of type String.
	 * @param page of type String.
	 * @return Collection&lt;CertificateDTO&gt;
	 */
	@GetMapping
	public Collection<CertificateDTO> query(
					@RequestParam(value = "name", required = false) String name,
					@RequestParam(value = "description", required = false) String description,
					@RequestParam(value = "price", required = false) String price,
					@RequestParam(value = "creation_date", required = false) String creationDate,
					@RequestParam(value = "modification_date", required = false) String modificationDate,
					@RequestParam(value = "duration", required = false) String duration,
					@RequestParam(value = "tag_name", required = false) String tagName,
					@RequestParam(value = "tag_id", required = false) String tagId,
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
		params.setTagName(tagName);
		params.setTagId(tagId);
		params.setSortBy(sortBy);
		params.setPerPage(perPage);
		params.setPage(page);
		return searchService.search(params);
	}
}
