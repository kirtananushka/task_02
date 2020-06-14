package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;

import java.util.Collection;
import java.util.Optional;

public interface CertificateRepository {

	Optional<Certificate> save(Certificate certificate);

	void remove(Certificate certificate);

	Optional<Certificate> update(Certificate certificate);

	Collection<Certificate> getAll();

	Optional<Certificate> getById(Long id);
}
