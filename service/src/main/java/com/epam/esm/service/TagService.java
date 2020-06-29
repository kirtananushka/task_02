package com.epam.esm.service;

import com.epam.esm.service.dto.TagDto;

import java.util.Optional;

public interface TagService {

	Optional<TagDto> getById(Long id);

	Optional<TagDto> save(TagDto tagDto);

	void remove(Long id);
}
