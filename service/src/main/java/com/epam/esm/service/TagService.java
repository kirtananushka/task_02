package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.Collection;
import java.util.Optional;

public interface TagService {

	Collection<Tag> getAll();

	Optional<Tag> getById(long id);

	Optional<Tag> save(Tag tag);

	void remove(long id);
}
