package com.epam.esm.service;

import com.epam.esm.service.dto.TagDTO;

import java.util.Collection;
import java.util.Optional;

public interface TagService {

	Collection<TagDTO> getAll();

	Optional<TagDTO> getById(Long id);

	Optional<TagDTO> save(TagDTO tagDTO);

	void remove(Long id);
}
