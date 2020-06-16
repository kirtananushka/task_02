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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TagController {

	private final TagService tagService;

	@GetMapping("/tags")
	public Collection<TagDTO> getTags() {
		return tagService.getAll();
	}

	@GetMapping("/tags/{tag_id}")
	public TagDTO getTag(@PathVariable Long tag_id) {
		return tagService.getById(tag_id).get();
	}

	@PostMapping("/tags")
	@ResponseStatus(HttpStatus.CREATED)
	public TagDTO addTag(@RequestBody TagDTO tagDTO) {
		tagDTO.setId(0L);
		return tagService.save(tagDTO).get();
	}

	@DeleteMapping("/tags/{tag_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeTag(@PathVariable Long tag_id) {
		tagService.remove(tag_id);
	}
}
