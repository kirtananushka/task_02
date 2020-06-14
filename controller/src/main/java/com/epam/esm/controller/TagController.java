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

import javax.servlet.http.HttpServletResponse;
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

	@GetMapping("/tags/{tag_id}")
	public Tag getTag(@PathVariable Long tag_id) {
		return tagService.getById(tag_id)
		                 .orElseThrow(EntityOperationException::new);
	}

	@PostMapping("/tags")
	public Tag addTag(@RequestBody Tag tag, HttpServletResponse response) {
		tag.setId(0L);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return tagService.save(tag)
		                 .orElseThrow(EntityOperationException::new);
	}

	@DeleteMapping("/tags/{tag_id}")
	public void removeTag(@PathVariable Long tag_id, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		if (tagService.getById(tag_id).isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		tagService.remove(tag_id);
	}
}
