package com.epam.esm.controller;

import com.epam.esm.parameterwrapper.ParameterWrapper;
import com.epam.esm.service.SearchService;
import com.epam.esm.service.dto.CertificateDTO;
import com.epam.esm.service.dto.TagDTO;
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
@RequestMapping("/api/v1")
public class SearchController {

	/**
	 * Field searchService
	 */
	private final SearchService searchService;

	/**
	 * GET method searchCertificate returns a collection of filtered<br>
	 * and/or sorted CertificateDTO objects.<br>
	 * <p>
	 * Get all:<br>
	 * [GET /api/v1/certificates]<br>
	 * <p>
	 * Filtering by name:<br>
	 * [GET /api/v1/certificates?name={name}]<br>
	 * <p>
	 * Filtering by description:<br>
	 * [GET /api/v1/certificates?description={description}]<br>
	 * <p>
	 * Filtering by price:<br>
	 * [GET /api/v1/certificates?price={price}]<br>
	 * [GET /api/v1/certificates?price=&gt;{price}]<br>
	 * [GET /api/v1/certificates?price=&lt;{price}]<br>
	 * [GET /api/v1/certificates?price=&lt;&gt;{price}]<br>
	 * [GET /api/v1/certificates?price=!={price}]<br>
	 * [GET /api/v1/certificates?price=between {price} and {price}]<br>
	 * [GET /api/v1/certificates?price=not between {price} and {price}]<br>
	 * <p>
	 * Filtering by creation date:<br>
	 * [GET /api/v1/certificates?creation_date={creation_date}]<br>
	 * [GET /api/v1/certificates?creation_date=&gt;{creation_date}]<br>
	 * [GET /api/v1/certificates?creation_date=&lt;{creation_date}]<br>
	 * [GET /api/v1/certificates?creation_date=&lt;&gt;{creation_date}]<br>
	 * [GET /api/v1/certificates?creation_date=!={creation_date}]<br>
	 * [GET /api/v1/certificates?creation_date=between {creation_date} and {creation_date}]<br>
	 * [GET /api/v1/certificates?creation_date=not between {creation_date} and {creation_date}]<br>
	 * <p>
	 * Filtering by modification date:<br>
	 * [GET /api/v1/certificates?
	 * modification_date={modification_date}]<br>
	 * [GET /api/v1/certificates?modification_date=&gt;{modification_date}]<br>
	 * [GET /api/v1/certificates?modification_date=&lt;{modification_date}]<br>
	 * [GET /api/v1/certificates?modification_date=&lt;&gt;{modification_date}]<br>
	 * [GET /api/v1/certificates?modification_date=!={modification_date}]<br>
	 * [GET /api/v1/certificates?modification_date=between {modification_date} and {modification_date}]<br>
	 * [GET /api/v1/certificates?modification_date=not between {modification_date} and {modification_date}]<br>
	 * <p>
	 * Filtering by duration:<br>
	 * [GET /api/v1/certificates?duration={duration}]<br>
	 * [GET /api/v1/certificates?duration=&gt;{duration}]<br>
	 * [GET /api/v1/certificates?duration=&lt;{duration}]<br>
	 * [GET /api/v1/certificates?duration=&lt;&gt;{duration}]<br>
	 * [GET /api/v1/certificates?duration=!={duration}]<br>
	 * [GET /api/v1/certificates?duration=between {duration} and {duration}]<br>
	 * [GET /api/v1/certificates?duration=not between {duration} and {duration}]<br>
	 * <p>
	 * Filtering by tad ID:<br>
	 * [GET /api/v1/certificates?tag_id={tag_id}]<br>
	 * [GET /api/v1/certificates?tag_id=&gt;{tag_id}]<br>
	 * [GET /api/v1/certificates?tag_id=&lt;{tag_id}]<br>
	 * [GET /api/v1/certificates?tag_id=&lt;&gt;{tag_id}]<br>
	 * [GET /api/v1/certificates?tag_id=!={tag_id}]<br>
	 * [GET /api/v1/certificates?tag_id=between {tag_id} and {tag_id}]<br>
	 * [GET /api/v1/certificates?tag_id=not between {tag_id} and {tag_id}]<br>
	 * <p>
	 * Filtering by tag name:<br>
	 * [GET /api/v1/certificates?tag_name={tag_name}]<br>
	 * <p>
	 * Filtering by multiple parameters:<br>
	 * [GET /api/v1/certificates?name={name}&amp;description={description}] etc.<br>
	 * <p>
	 * Sorting in ascending order by certificate ID:<br>
	 * [GET /api/v1/certificates?sort=certificates.id]<br>
	 * <p>
	 * Sorting in descending order by certificate ID:<br>
	 * [GET /api/v1/certificates?sort=-certificates.id]<br>
	 * <p>
	 * Sorting in ascending order by certificate name:<br>
	 * [GET /api/v1/certificates?sort=name]<br>
	 * <p>
	 * Sorting in descending order by certificate name:<br>
	 * [GET /api/v1/certificates?sort=-name]<br>
	 * <p>
	 * Sorting in ascending order by description:<br>
	 * [GET /api/v1/certificates?sort=description]<br>
	 * <p>
	 * Sorting in descending order by description:<br>
	 * [GET /api/v1/certificates?sort=-description]<br>
	 * <p>
	 * Sorting in ascending order by price:<br>
	 * [GET /api/v1/certificates?sort=price]<br>
	 * <p>
	 * Sorting in descending order by price:<br>
	 * [GET /api/v1/certificates?sort=-price]<br>
	 * <p>
	 * Sorting in ascending order by creation date:<br>
	 * [GET /api/v1/certificates?sort=creation_date]<br>
	 * <p>
	 * Sorting in descending order by creation date:<br>
	 * [GET /api/v1/certificates?sort=-creation_date]<br>
	 * <p>
	 * Sorting in ascending order by modification date:<br>
	 * [GET /api/v1/certificates?sort=modification_date]<br>
	 * <p>
	 * Sorting in descending order by modification date:<br>
	 * [GET /api/v1/certificates?sort=-modification_date]<br>
	 * <p>
	 * Sorting in ascending order by duration:<br>
	 * [GET /api/v1/certificates?sort=duration]<br>
	 * <p>
	 * Sorting in descending order by duration:<br>
	 * [GET /api/v1/certificates?sort=-duration]<br>
	 * <p>
	 * Sorting by multiple parameters:<br>
	 * [GET /api/v1/certificates?sort=-name,duration,-price] etc.<br>
	 * <p>
	 * Filtering and sorting by multiple parameters:<br>
	 * [GET /api/v1/certificates?name={name}&amp;sort=-name,duration,-price] etc.<br>
	 * <p>
	 * Pagination (by default, 50 entries per page):<br>
	 * [GET /api/v1/certificates?page=1&amp;size=10] etc.<br>
	 * <p>
	 * Request (application/json).<br>
	 * Response 200 (application/json).<br>
	 * <p>
	 *
	 * @param name             of type String.
	 * @param description      of type String.
	 * @param price            of type String.
	 * @param creationDate     of type String.
	 * @param modificationDate of type String.
	 * @param duration         of type String.
	 * @param tagName          of type String.
	 * @param tagId            of type String.
	 * @param sortBy           of type String.
	 * @param size          of type String.
	 * @param page             of type String.
	 * @return Collection&lt;CertificateDTO&gt;
	 */
	@GetMapping("/certificates")
	public Collection<CertificateDTO> searchCertificate(
					@RequestParam(value = "name", required = false) String name,
					@RequestParam(value = "description", required = false) String description,
					@RequestParam(value = "price", required = false) String price,
					@RequestParam(value = "creation_date", required = false) String creationDate,
					@RequestParam(value = "modification_date", required = false) String modificationDate,
					@RequestParam(value = "duration", required = false) String duration,
					@RequestParam(value = "tag_name", required = false) String tagName,
					@RequestParam(value = "tag_id", required = false) String tagId,
					@RequestParam(value = "sort", required = false, defaultValue = "certificates.id") String sortBy,
					@RequestParam(value = "size", required = false, defaultValue = "50") String size,
					@RequestParam(value = "page", required = false, defaultValue = "1") String page) {
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
		params.setSize(size);
		params.setPage(page);
		return searchService.searchCertificate(params);
	}

	/**
	 * GET method searchTag returns a collection of TagDTO objects.<br>
	 * <p>
	 * Get all:<br>
	 * [GET /api/v1/tags]<br>
	 * <p>
	 * Pagination (by default, 50 entries per page):<br>
	 * [GET /api/v1/tags?page=1&amp;size=10] etc.<br>
	 * <p>
	 * Request (application/json).<br>
	 * Response 200 (application/json).
	 * <p>
	 *
	 * @param size of type String
	 * @param page of type String
	 * @return collection of TagDTO objects (type Collection&lt;TagDTO&gt;).
	 */
	@GetMapping("/tags")
	public Collection<TagDTO> searchTag(
					@RequestParam(value = "size", required = false, defaultValue = "50") String size,
					@RequestParam(value = "page", required = false, defaultValue = "1") String page) {
		ParameterWrapper params = new ParameterWrapper();
		params.setSize(size);
		params.setPage(page);
		return searchService.searchTag(params);
	}
}
