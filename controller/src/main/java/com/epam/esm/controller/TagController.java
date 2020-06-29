package com.epam.esm.controller;

import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.TagDto;
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

import java.util.Objects;

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
	 * GET method getTag returns TagDto object.
	 * <p>
	 * [GET /api/v1/tags/{id}]<br>
	 * Request (application/json).<br>
	 * Response 200 (application/json).
	 * <p>
	 * Example json:<br>
	 * {<br>
	 * "id": 1,<br>
	 * "name": "Suspendisse"<br>
	 * }
	 * <p>
	 *
	 * @param id of type Long.
	 * @return TagDto.
	 */
	@GetMapping("{id}")
	public TagDto getTag(@PathVariable Long id) {
		return tagService.getById(id).get();
	}

	/**
	 * Post method addTag adds TagDto object.
	 * <p>
	 * [POST /api/v1/tags/]<br>
	 * Parameters: tag (TagDto, required).<br>
	 * Request (application/json).<br>
	 * Response 200 (application/json).
	 * <p>
	 * Example json:<br>
	 * {<br>
	 * "name": "Suspendisse"<br>
	 * }
	 * <p>
	 *
	 * @param tagDto of type TagDto.
	 * @return TagDto.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TagDto addTag(@RequestBody TagDto tagDto) {
		if (Objects.isNull(tagDto.getId())) {
			tagDto.setId(0L);
		}
		return tagService.save(tagDto).get();
	}

	/**
	 * DELETE method removeTag removes TagDto object by ID.
	 * <p>
	 * [DELETE /api/v1/tags/{id}]<br>
	 * Parameters: tag id (Long, required) - unique tag ID.<br>
	 * Request (application/json).<br>
	 * Response 204 (application/json).
	 * <p>
	 *
	 * @param id of type Long.
	 */
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeTag(@PathVariable Long id) {
		tagService.remove(id);
	}
}