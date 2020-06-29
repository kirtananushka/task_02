package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.RepositoryNotFoundException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.ErrorMessage;
import com.epam.esm.service.ServiceConflictRequestException;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.ServiceNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.ModelMapper;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.validation.EntityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {

	private final TagRepository tagRepository;

	@Override
	public Optional<TagDto> getById(Long id) {
		if (!EntityValidator.checkLong(id)) {
			throw new ServiceException(ErrorMessage.ERROR_INVALID_TAG_ID + id);
		}
		Tag tag = tagRepository.getById(id).orElseThrow(() -> new ServiceNotFoundException(
						ErrorMessage.ERROR_NO_TAG_WITH_ID + id));
		if (Objects.isNull(tag)) {
			throw new ServiceException(
							ErrorMessage.ERROR_NO_TAG_WITH_ID + id);
		}
		return ModelMapper.convertToTagDto(tag);
	}

	@Override
	public Optional<TagDto> save(TagDto tagDto) {
		EntityValidator.validate(tagDto);
		checkIdUniqueness(tagDto);
		if (Objects.isNull(tagDto.getName())) {
			throw new ServiceException(
							ErrorMessage.ERROR_MISSING_TAG_NAME);
		}
		Tag tag = ModelMapper.convertToTag(tagDto).get();
		tag = tagRepository.save(tag).orElseThrow(
						() -> new ServiceException(ErrorMessage.ERROR_TAG_NOT_CREATED));
		if (Objects.isNull(tag)) {
			throw new ServiceException(ErrorMessage.ERROR_TAG_NOT_CREATED);
		}
		return ModelMapper.convertToTagDto(tag);
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

	private void checkIdUniqueness(TagDto tagDto) {
		try {
			if (tagRepository.getById(tagDto.getId()).isPresent()) {
				throw new ServiceConflictRequestException(
								ErrorMessage.ERROR_CONFLICT_TAG_ID_EXISTS + tagDto.getId());
			}
		} catch (RepositoryNotFoundException e) {
			if (!tagDto.getId().equals(0L)) {
				throw new ServiceConflictRequestException(
								ErrorMessage.ERROR_CONFLICT_TAG_ID_NEW);
			}
		}
	}
}
