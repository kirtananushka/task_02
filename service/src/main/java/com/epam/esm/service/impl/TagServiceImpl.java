package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

	private final TagRepository tagRepository;

	@Autowired
	public TagServiceImpl(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Override
	public Collection<Tag> getAll() {
		Collection<Tag> list = tagRepository.getAll();
		return list;
	}

	@Override
	public Optional<Tag> getById(long id) {
		return tagRepository.getById(id);
	}

	@Override
	public Optional<Tag> save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public void remove(long id) {
		if (getById(id).isPresent()) {
			tagRepository.remove(getById(id).get());
		}
	}
}
