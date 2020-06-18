package com.epam.esm.service.impl;

import com.epam.esm.parameterwrapper.ParameterWrapper;
import com.epam.esm.repository.SearchRepository;
import com.epam.esm.service.ErrorMessage;
import com.epam.esm.service.SearchService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.dto.CertificateDTO;
import com.epam.esm.service.dto.ModelMapper;
import com.epam.esm.service.dto.TagDTO;
import com.epam.esm.service.validation.EntityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchServiceImpl implements SearchService {

	private final SearchRepository searchRepository;

	@Override
	public Collection<CertificateDTO> searchCertificate(ParameterWrapper params) {
		if (Objects.nonNull(params.getPrice())
						&& !EntityValidator.checkPriceQuery(params.getPrice())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_PRICE_EXPR + params.getPrice());
		}
		if (Objects.nonNull(params.getCreationDate())
						&& !EntityValidator.checkDateQuery(params.getCreationDate())) {
			throw new ServiceException(
							ErrorMessage.ERROR_CREATION_DATE_EXPR + params.getCreationDate());
		}
		if (Objects.nonNull(params.getModificationDate())
						&& !EntityValidator.checkDateQuery(params.getModificationDate())) {
			throw new ServiceException(
							ErrorMessage.ERROR_MODIFICATION_DATE_EXPR + params.getModificationDate());
		}
		if (Objects.nonNull(params.getDuration())
						&& !EntityValidator.checkIntegerQuery(params.getDuration())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_DURATION_EXPR + params.getDuration());
		}
		if (Objects.nonNull(params.getTagId())
						&& !EntityValidator.checkIntegerQuery(params.getTagId())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_TAG_ID_EXPR + params.getTagId());
		}
		if (Objects.nonNull(params.getPage())
						&& !EntityValidator.checkIntegerQuery(params.getPage())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_PAGE + params.getPage());
		}
		if (Objects.nonNull(params.getSize())
						&& !EntityValidator.checkIntegerQuery(params.getSize())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_SIZE_PER_PAGE + params.getSize());
		}
		if (Objects.nonNull(params.getSortBy())) {
			for (String param : params.getSortBy().split(",")) {
				if (!EntityValidator.checkSortBy(param)) {
					throw new ServiceException(
									ErrorMessage.ERROR_INCORRECT_PARAM_SORTING + param);
				}
			}
		}
		return searchRepository.searchCertificate(params)
		                       .stream()
		                       .map(ModelMapper::convertToCertificateDTO)
		                       .map(Optional::get)
		                       .collect(Collectors.toList());
	}

	@Override
	public Collection<TagDTO> searchTag(ParameterWrapper params) {
		if (Objects.nonNull(params.getPage())
						&& !EntityValidator.checkIntegerQuery(params.getPage())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_PAGE + params.getPage());
		}
		if (Objects.nonNull(params.getSize())
						&& !EntityValidator.checkIntegerQuery(params.getSize())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_SIZE_PER_PAGE + params.getSize());
		}
		return searchRepository.searchTag(params)
		                       .stream()
		                       .map(ModelMapper::convertToTagDTO)
		                       .map(Optional::get)
		                       .collect(Collectors.toList());
	}
}
