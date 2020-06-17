package com.epam.esm.controller;

import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.TagDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Class TagController for task 2.
 *
 * @author KIR TANANUSHKA
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {

	/**
	 * Field tagService
	 */
	private final TagService tagService;

	/**
	 * GET method getTags returns collection of TagDTO objects.
	 * <p>
	 * [GET /api/v1/tags/]
	 * Request (application/json).
	 * Response 200 (application/json).
	 *
	 * @return collection of TagDTO objects (type Collection&lt;TagDTO&gt;).
	 */
	@GetMapping
	public Collection<TagDTO> getTags() {
		return tagService.getAll();
	}

	/**
	 * GET method getTag returns TagDTO object.
	 * <p>
	 * [GET /api/v1/tags/{id}]
	 * Request (application/json).
	 * Response 200 (application/json).
	 * <p>
	 * Example json:
	 * {
	 * "id": 1,
	 * "name": "Suspendisse"
	 * }
	 *
	 * @param id of type Long.
	 * @return TagDTO.
	 */
	@GetMapping("{id}")
	public TagDTO getTag(@PathVariable Long id) {
		return tagService.getById(id).get();
	}

	/**
	 * Post method addTag adds TagDTO object.
	 * <p>
	 * [POST /api/v1/tags/]
	 * Parameters: tag (TagDTO, required).
	 * Request (application/json).
	 * Response 200 (application/json).
	 * <p>
	 * Example json:
	 * {
	 * "name": "Suspendisse"
	 * }
	 *
	 * @param tagDTO of type TagDTO.
	 * @return TagDTO.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TagDTO addTag(@RequestBody TagDTO tagDTO) {
		tagDTO.setId(0L);
		return tagService.save(tagDTO).get();
	}

	/**
	 * DELETE method removeTag removes TagDTO object by ID.
	 * <p>
	 * [DELETE /api/v1/tags/{id}]
	 * Parameters: tag id (Long, required) - unique tag ID.
	 * Request (application/json).
	 * Response 204 (application/json).
	 *
	 * @param id of type Long.
	 */
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeTag(@PathVariable Long id) {
		tagService.remove(id);
	}
}