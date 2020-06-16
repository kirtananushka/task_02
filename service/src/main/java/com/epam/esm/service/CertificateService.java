package com.epam.esm.service;

import com.epam.esm.service.dto.CertificateDTO;

import java.util.Optional;

public interface CertificateService {

	Optional<CertificateDTO> getById(Long id);

	Optional<CertificateDTO> save(CertificateDTO certificate);

	Optional<CertificateDTO> update(CertificateDTO certificate);

	void remove(Long id);
}
