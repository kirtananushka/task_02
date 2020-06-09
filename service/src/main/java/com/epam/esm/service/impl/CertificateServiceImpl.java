package com.epam.esm.service.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

	private CertificateRepository certificateRepository;

	@Autowired
	public CertificateServiceImpl(CertificateRepository certificateRepository) {
		this.certificateRepository = certificateRepository;
	}

	@Override
	public List<Certificate> getCertificates() {
		List<Certificate> list = certificateRepository.getCertificates();
		return list;
	}

	@Override
	public Optional<Certificate> getCertificate(long id) {
		return certificateRepository.getCertificate(id);
	}

	@Override
	public Optional<Certificate> addCertificate(Certificate certificate) {
		certificate.setCreationDate(LocalDate.now());
		return certificateRepository.addCertificate(certificate);
	}
}
