package com.epam.esm.service;

import com.epam.esm.entity.Certificate;

import java.util.Collection;
import java.util.Optional;

public interface CertificateService {

	Collection<Certificate> getAll();

	Optional<Certificate> getById(long id);

	Optional<Certificate> save(Certificate certificate);

	Optional<Certificate> update(Certificate certificate);

	void remove(long id);
}
