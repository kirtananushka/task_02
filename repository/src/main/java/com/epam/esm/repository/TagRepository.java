package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TagRepository {

	Optional<Tag> save(Tag tag);

	void remove(Tag tag);

	Collection<Tag> getAll();

	Optional<Tag> getById(Long id);

	List<Tag> getTagsByCertificateId(Long id);

	void bindCertificateTag(Certificate certificate);
}
