package com.epam.esm.service.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

	private CertificateRepository certificateRepository;

	@Autowired
	public CertificateServiceImpl(CertificateRepository certificateRepository) {
		this.certificateRepository = certificateRepository;
	}

	@Override
	public Collection<Certificate> getAll() {
		Collection<Certificate> list = certificateRepository.getAll();
		return list;
	}

	@Override
	public Optional<Certificate> getById(long id) {
		return certificateRepository.getById(id);
	}

	@Override
	public Optional<Certificate> save(Certificate certificate) {
		certificate.setCreationDate(LocalDate.now());
		return certificateRepository.save(certificate);
	}

	@Override
	public Optional<Certificate> update(Certificate certificate) {
		certificate.setModificationDate(LocalDate.now());
		return certificateRepository.update(certificate);
	}

	@Override
	public void remove(long id) {
		if (getById(id).isPresent()) {
			certificateRepository.remove(getById(id).get());
		}
	}
}
