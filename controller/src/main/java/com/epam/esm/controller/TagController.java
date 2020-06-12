package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.EntityOperationException;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TagController {

	private final TagService tagService;

	@GetMapping("/tags")
	public Collection<Tag> getTags() {
		return tagService.getAll();
	}

	@GetMapping("/tags/{id}")
	public Tag getTag(@PathVariable long id) {
		return tagService.getById(id)
		                 .orElseThrow(() -> new EntityOperationException(
						                 "Tag id was not found: " + id));
	}

	@PostMapping("/tags")
	public Tag addTag(@RequestBody Tag tag) {
		tag.setId(0L);
		return tagService.save(tag)
		                 .orElseThrow(() -> new EntityOperationException(
						                 "Tag was not added"));
	}

	@DeleteMapping("/tags/{id}")
	public String removeTag(@PathVariable long id) {
		if (tagService.getById(id).isEmpty()) {
			throw new EntityOperationException("Customer id was not found: " + id);
		}
		tagService.remove(id);
		return "Tag was deleted; id: " + id;
	}
}
