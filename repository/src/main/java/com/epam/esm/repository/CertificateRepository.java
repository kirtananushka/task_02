package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;
import com.epam.esm.specification.CertificateSpecification;

import java.util.List;
import java.util.Optional;

public interface CertificateRepository {

	Optional<Certificate> addCertificate(Certificate certificate);

	void removeCertificate(Certificate certificate);

	void updateCertificate(Certificate certificate);

	List<Certificate> query(CertificateSpecification specification);

	List<Certificate> getCertificates();

	Optional<Certificate> getCertificate(long id);
}
