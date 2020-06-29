package com.epam.esm.service;

import com.epam.esm.service.dto.CertificateDto;

import java.util.Optional;

public interface CertificateService {

	Optional<CertificateDto> getById(Long id);

	Optional<CertificateDto> save(CertificateDto certificate);

	Optional<CertificateDto> update(CertificateDto certificate);

	void remove(Long id);
}
