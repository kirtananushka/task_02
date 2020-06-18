package com.epam.esm.service.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.ErrorMessage;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.ServiceNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.CertificateDTO;
import com.epam.esm.service.dto.ModelMapper;
import com.epam.esm.service.dto.TagDTO;
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
	public Optional<CertificateDTO> getById(Long id) {
		if (!EntityValidator.checkLong(id)) {
			throw new ServiceException(
							ErrorMessage.ERROR_INVALID_CERTIFICATE_ID + id);
		}
		Optional<Certificate> certificateOptional;
		try {
			certificateOptional = certificateRepository.getById(id);
		} catch (Exception e) {
			throw new ServiceNotFoundException(
							ErrorMessage.ERROR_NO_CERTIFICATE_WITH_ID + id, e);
		}
		if (certificateOptional.isEmpty()) {
			throw new ServiceException(
							ErrorMessage.ERROR_NO_CERTIFICATE_WITH_ID + id);
		}
		return ModelMapper.convertToCertificateDTO(certificateOptional.get());
	}

	@Override
	public Optional<CertificateDTO> save(CertificateDTO certificateDTO) {
		EntityValidator.validate(certificateDTO);
		checkTags(certificateDTO);
		Certificate certificate = ModelMapper.convertToCertificate(certificateDTO).get();
		certificate.setCreationDate(LocalDate.now());
		certificate.setModificationDate(null);
		Optional<Certificate> optionalCertificate;
		try {
			optionalCertificate = certificateRepository.save(certificate);
		} catch (Exception e) {
			throw new ServiceException(
							ErrorMessage.ERROR_CERTIFICATE_NOT_CREATED, e);
		}
		if (optionalCertificate.isEmpty()) {
			throw new ServiceException(
							ErrorMessage.ERROR_CERTIFICATE_NOT_CREATED);
		}
		certificate = optionalCertificate.get();
		if (Objects.isNull(certificate)) {
			throw new ServiceException(
							ErrorMessage.ERROR_CERTIFICATE_NOT_CREATED);
		}
		return ModelMapper.convertToCertificateDTO(certificate);
	}

	@Override
	public Optional<CertificateDTO> update(CertificateDTO certificateDTO) {
		EntityValidator.validate(certificateDTO);
		try {
			certificateRepository.getById(certificateDTO.getId()).isPresent();
		} catch (Exception e) {
			throw new ServiceNotFoundException(
							ErrorMessage.ERROR_NO_CERTIFICATE_WITH_ID + certificateDTO.getId());
		}
		checkTags(certificateDTO);
		Certificate certificate = ModelMapper.convertToCertificate(certificateDTO).get();
		certificate.setModificationDate(LocalDate.now());
		try {
			certificate = certificateRepository.update(certificate).get();
		} catch (Exception e) {
			throw new ServiceException(ErrorMessage.ERROR_CERTIFICATE_NOT_UPDATED, e);
		}
		if (Objects.isNull(certificate)) {
			throw new ServiceException(ErrorMessage.ERROR_CERTIFICATE_NOT_UPDATED);
		}
		return ModelMapper.convertToCertificateDTO(certificate);
	}

	@Override
	public void remove(Long id) {
		if (getById(id).isPresent()) {
			try {
				Optional<Certificate> certificateOptional = certificateRepository.getById(id);
				certificateRepository.remove(certificateOptional.get());
			} catch (Exception e) {
				throw new ServiceException(
								ErrorMessage.ERROR_CERTIFICATE_NOT_DELETED, e);
			}
		}
	}

	private void checkTags(CertificateDTO certificateDTO) {
		if (Objects.isNull(certificateDTO.getTags())) {
			return;
		}
		if (certificateDTO.getTags().isEmpty()) {
			return;
		}
		for (TagDTO tagDTO : certificateDTO.getTags()) {
			if (Objects.isNull(tagDTO.getId()) && Objects.isNull(tagDTO.getName())) {
				continue;
			}
			if (Objects.nonNull(tagDTO.getId()) && Objects.isNull(tagDTO.getName())
							&& !EntityValidator.checkLong(tagDTO.getId())) {
				throw new ServiceException(
								ErrorMessage.ERROR_INVALID_TAG_ID + tagDTO.getId());
			}
			if (Objects.nonNull(tagDTO.getId()) && Objects.isNull(tagDTO.getName())
							&& tagService.getById(tagDTO.getId()).isEmpty()) {
				throw new ServiceException(
								ErrorMessage.ERROR_ADD_TAG_WITHOUT_NAME + tagDTO.getId());
			}
			if (Objects.nonNull(tagDTO.getId()) && Objects.nonNull(tagDTO.getName())) {
				if (!EntityValidator.checkLong(tagDTO.getId())) {
					throw new ServiceException(
									ErrorMessage.ERROR_INVALID_TAG_ID + tagDTO.getId());
				}
				if (!EntityValidator.checkText(tagDTO.getName())) {
					throw new ServiceException(
									ErrorMessage.ERROR_INCORRECT_TAG_NAME_LENGTH + tagDTO.getName());
				}
				if (tagService.getById(tagDTO.getId()).isPresent()
								&& !tagService.getById(tagDTO.getId()).get().getName().equals(tagDTO.getName())) {
					throw new ServiceException(
									ErrorMessage.ERROR_TAG_ID_NAME_NOT_RELEVANT + tagDTO.getId()
													+ ErrorMessage.EXPECTED_NAME
													+ tagService.getById(tagDTO.getId()).get().getName()
													+ ErrorMessage.ERROR_NAME + tagDTO.getName());
				}
			}
		}
	}
}
