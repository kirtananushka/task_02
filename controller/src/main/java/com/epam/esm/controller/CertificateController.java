package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.EntityOperationException;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CertificateController {

	private CertificateService certificateService;

	@Autowired
	public CertificateController(CertificateService certificateService) {
		this.certificateService = certificateService;
	}

	@GetMapping("/certificates")
	public List<Certificate> getCertificates() {
		return certificateService.getCertificates();
	}

	@GetMapping("/certificates/{id}")
	public Certificate getCertificate(@PathVariable long id) {
		return certificateService.getCertificate(id)
		                         .orElseThrow(() -> new EntityOperationException(
						                         "Certificate id was not found: " + id));
	}

	@PostMapping("/certificates")
	public Certificate addCertificate(@RequestBody Certificate certificate) {
		certificate.setCertId(0);
		return certificateService.addCertificate(certificate)
		                         .orElseThrow(() -> new EntityOperationException(
						                         "Certificate was not added: "));
	}
}
