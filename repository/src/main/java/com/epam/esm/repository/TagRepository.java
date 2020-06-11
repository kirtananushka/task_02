package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import com.epam.esm.specification.TagSpecification;

import java.util.Collection;
import java.util.Optional;

public interface TagRepository {

	Optional<Tag> save(Tag tag);

	void remove(Tag tag);

	Collection<Tag> query(TagSpecification specification);

	Collection<Tag> getAll();

	Optional<Tag> getById(long id);
}
