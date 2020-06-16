package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.ErrorMessage;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.TagDTO;
import com.epam.esm.service.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

	private final TagRepository tagRepository;

	@Override
	public Collection<TagDTO> getAll() {
		return tagRepository.getAll()
		                    .stream()
		                    .map(this::convertToTagDTO)
		                    .map(Optional::get)
		                    .collect(Collectors.toList());
	}

	@Override
	public Optional<TagDTO> getById(Long id) {
		if (!Validator.checkLong(id)) {
			throw new ServiceException(ErrorMessage.ERROR_INVALID_TAG_ID + id);
		}
		Optional<Tag> tagOptional;
		try {
			tagOptional = tagRepository.getById(id);
		} catch (Exception e) {
			throw new ServiceException(
							ErrorMessage.ERROR_NO_TAG_WITH_ID + id, e);
		}
		if (tagOptional.isEmpty()) {
			throw new ServiceException(
							ErrorMessage.ERROR_NO_TAG_WITH_ID + id);
		}
		return convertToTagDTO(tagOptional.get());
	}

	@Override
	public Optional<TagDTO> save(TagDTO tagDTO) {
		if (!Validator.checkLong(tagDTO.getId())) {
			throw new ServiceException(ErrorMessage.ERROR_INVALID_TAG_ID + tagDTO.getId());
		}
		if (Objects.isNull(tagDTO.getName()) || !Validator
						.checkText(tagDTO.getName())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INCORRECT_TAG_NAME_LENGTH + tagDTO.getName().length());
		}
		Tag tag = convertToTag(tagDTO).get();
		try {
			tag = tagRepository.save(tag).get();
		} catch (Exception e) {
			throw new ServiceException(ErrorMessage.ERROR_TAG_NOT_CREATED, e);
		}
		if (Objects.isNull(tag)) {
			throw new ServiceException(ErrorMessage.ERROR_TAG_NOT_CREATED);
		}
		return convertToTagDTO(tag);
	}

	@Override
	public void remove(Long id) {
		if (getById(id).isPresent()) {
			try {
				Optional<Tag> tagOptional = tagRepository.getById(id);
				tagRepository.remove(tagOptional.get());
			} catch (Exception e) {
				throw new ServiceException(ErrorMessage.ERROR_TAG_NOT_DELETED, e);
			}
		}
	}

	private Optional<Tag> convertToTag(TagDTO tagDTO) {
		Tag tag = new Tag();
		try {
			tag.setId(tagDTO.getId());
			tag.setName(tagDTO.getName());
		} catch (Exception e) {
			throw new ServiceException(ErrorMessage.ERROR_TAG_CONVERSION, e);
		}
		return Optional.of(tag);
	}

	private Optional<TagDTO> convertToTagDTO(Tag tag) {
		TagDTO tagDTO = new TagDTO();
		try {
			tagDTO.setId(tag.getId());
			tagDTO.setName(tag.getName());
		} catch (Exception e) {
			throw new ServiceException(ErrorMessage.ERROR_TAG_CONVERSION, e);
		}
		return Optional.of(tagDTO);
	}
}
