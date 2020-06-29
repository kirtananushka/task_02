package com.epam.esm.service.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.RepositoryNotFoundException;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.ErrorMessage;
import com.epam.esm.service.ServiceConflictRequestException;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.ServiceNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.dto.ModelMapper;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.validation.EntityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificateServiceImpl implements CertificateService {

	private final CertificateRepository certificateRepository;
	private final TagService tagService;

	@Override
	public Optional<CertificateDto> getById(Long id) {
		if (!EntityValidator.checkLong(id)) {
			throw new ServiceException(ErrorMessage.ERROR_INVALID_CERTIFICATE_ID + id);
		}
		Certificate certificate = certificateRepository.getById(id).orElseThrow(
						() -> new ServiceNotFoundException(ErrorMessage.ERROR_NO_CERTIFICATE_WITH_ID + id));
		if (Objects.isNull(certificate)) {
			throw new ServiceException(ErrorMessage.ERROR_NO_CERTIFICATE_WITH_ID + id);
		}
		return ModelMapper.convertToCertificateDto(certificate);
	}

	@Override
	public Optional<CertificateDto> save(CertificateDto certificateDto) {
		EntityValidator.validate(certificateDto);
		checkIdUniqueness(certificateDto);
		checkTags(certificateDto);
		Certificate certificate = ModelMapper.convertToCertificate(certificateDto).get();
		certificate.setCreationDate(LocalDate.now());
		certificate.setModificationDate(null);
		certificate = certificateRepository.save(certificate).orElseThrow(
						() -> new ServiceException(ErrorMessage.ERROR_CERTIFICATE_NOT_CREATED));
		if (Objects.isNull(certificate)) {
			throw new ServiceException(ErrorMessage.ERROR_CERTIFICATE_NOT_CREATED);
		}
		return ModelMapper.convertToCertificateDto(certificate);
	}

	@Override
	public Optional<CertificateDto> update(CertificateDto certificateDto) {
		EntityValidator.validate(certificateDto);
		certificateRepository.getById(certificateDto.getId()).orElseThrow(
						() -> new ServiceNotFoundException(ErrorMessage.ERROR_NO_CERTIFICATE_WITH_ID + certificateDto.getId()));
		checkTags(certificateDto);
		Certificate certificate = ModelMapper.convertToCertificate(certificateDto).get();
		certificate.setModificationDate(LocalDate.now());
		certificate = certificateRepository.update(certificate).orElseThrow(
						() -> new ServiceException(ErrorMessage.ERROR_CERTIFICATE_NOT_UPDATED));
		if (Objects.isNull(certificate)) {
			throw new ServiceException(ErrorMessage.ERROR_CERTIFICATE_NOT_UPDATED);
		}
		return ModelMapper.convertToCertificateDto(certificate);
	}

	@Override
	public void remove(Long id) {
		if (getById(id).isPresent()) {
			try {
				Optional<Certificate> certificateOptional = certificateRepository.getById(id);
				certificateRepository.remove(certificateOptional.get());
			} catch (Exception e) {
				throw new ServiceException(ErrorMessage.ERROR_CERTIFICATE_NOT_DELETED, e);
			}
		}
	}

	private void checkIdUniqueness(CertificateDto certificateDto) {
		try {
			if (certificateRepository.getById(certificateDto.getId()).isPresent()) {
				throw new ServiceConflictRequestException(
								ErrorMessage.ERROR_CONFLICT_CERTIFICATE_ID_EXISTS + certificateDto.getId());
			}
		} catch (RepositoryNotFoundException e) {
			if (!certificateDto.getId().equals(0L)) {
				throw new ServiceConflictRequestException(
								ErrorMessage.ERROR_CONFLICT_CERTIFICATE_ID_NEW);
			}
		}
	}

	private void checkTags(CertificateDto certificateDto) {
		if (Objects.isNull(certificateDto.getTags())) {
			return;
		}
		if (certificateDto.getTags().isEmpty()) {
			return;
		}
		for (TagDto tagDto : certificateDto.getTags()) {
			if (Objects.isNull(tagDto.getId()) && Objects.isNull(tagDto.getName())) {
				continue;
			}
			if (Objects.nonNull(tagDto.getId()) && Objects.isNull(tagDto.getName()) && !EntityValidator
							.checkLong(tagDto.getId())) {
				throw new ServiceException(ErrorMessage.ERROR_INVALID_TAG_ID + tagDto.getId());
			}
			if (Objects.nonNull(tagDto.getId()) && Objects.isNull(tagDto.getName()) && tagService.getById(tagDto.getId())
			                                                                                     .isEmpty()) {
				throw new ServiceException(ErrorMessage.ERROR_ADD_TAG_WITHOUT_NAME + tagDto.getId());
			}
			if (Objects.nonNull(tagDto.getId()) && Objects.nonNull(tagDto.getName())) {
				if (!EntityValidator.checkLong(tagDto.getId())) {
					throw new ServiceException(ErrorMessage.ERROR_INVALID_TAG_ID + tagDto.getId());
				}
				if (!EntityValidator.checkText(tagDto.getName())) {
					throw new ServiceException(ErrorMessage.ERROR_INCORRECT_TAG_NAME_LENGTH + tagDto.getName());
				}
				if (tagService.getById(tagDto.getId()).isPresent() && !tagService.getById(tagDto.getId()).get().getName()
				                                                                 .equals(tagDto.getName())) {
					throw new ServiceConflictRequestException(
									ErrorMessage.ERROR_TAG_ID_NAME_NOT_RELEVANT + tagDto.getId()
													+ ErrorMessage.EXPECTED_NAME + tagService.getById(tagDto.getId()).get().getName()
													+ ErrorMessage.ERROR_NAME + tagDto.getName());
				}
			}
		}
	}
}
