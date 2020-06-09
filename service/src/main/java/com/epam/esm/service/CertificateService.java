package com.epam.esm.service;

import com.epam.esm.entity.Certificate;

import java.util.List;
import java.util.Optional;

public interface CertificateService {

	List<Certificate> getCertificates();

	Optional<Certificate> getCertificate(long id);

	Optional<Certificate> addCertificate(Certificate certificate);
}
