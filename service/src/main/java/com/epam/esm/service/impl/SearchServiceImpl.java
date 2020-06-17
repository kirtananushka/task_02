package com.epam.esm.service.impl;

import com.epam.esm.parameterwrapper.ParameterWrapper;
import com.epam.esm.repository.SearchRepository;
import com.epam.esm.service.ErrorMessage;
import com.epam.esm.service.SearchService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.dto.CertificateDTO;
import com.epam.esm.service.dto.ModelMapper;
import com.epam.esm.service.validation.Validator;
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
	public Collection<CertificateDTO> search(ParameterWrapper params) {
		if (Objects.nonNull(params.getPrice())
						&& !Validator.checkPriceQuery(params.getPrice())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_PRICE_EXPR + params.getPrice());
		}
		if (Objects.nonNull(params.getCreationDate())
						&& !Validator.checkDateQuery(params.getCreationDate())) {
			throw new ServiceException(
							ErrorMessage.ERROR_CREATION_DATE_EXPR + params.getCreationDate());
		}
		if (Objects.nonNull(params.getModificationDate())
						&& !Validator.checkDateQuery(params.getModificationDate())) {
			throw new ServiceException(
							ErrorMessage.ERROR_MODIFICATION_DATE_EXPR + params.getModificationDate());
		}
		if (Objects.nonNull(params.getDuration())
						&& !Validator.checkIntegerQuery(params.getDuration())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_DURATION_EXPR + params.getDuration());
		}
		if (Objects.nonNull(params.getTagId())
						&& !Validator.checkIntegerQuery(params.getTagId())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_TAG_ID_EXPR + params.getTagId());
		}
		if (Objects.nonNull(params.getPage())
						&& !Validator.checkIntegerQuery(params.getPage())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_PAGE + params.getPage());
		}
		if (Objects.nonNull(params.getPerPage())
						&& !Validator.checkIntegerQuery(params.getPerPage())) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_PER_PAGE + params.getPerPage());
		}
		if (Objects.nonNull(params.getSortBy())) {
			for (String param : params.getSortBy().split(",")) {
				if (!Validator.checkSortBy(param)) {
					throw new ServiceException(
									ErrorMessage.ERROR_INCORRECT_PARAM_SORTING + param);
				}
			}
		}
		return searchRepository.search(params)
		                       .stream()
		                       .map(ModelMapper::convertToCertificateDTO)
		                       .map(Optional::get)
		                       .collect(Collectors.toList());
	}
}
