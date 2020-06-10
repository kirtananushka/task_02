package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.EntityOperationException;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1")
public class CertificateController {

	private CertificateService certificateService;

	@Autowired
	public CertificateController(CertificateService certificateService) {
		this.certificateService = certificateService;
	}

	@GetMapping("/certificates")
	public Collection<Certificate> getCertificates() {
		return certificateService.getAll();
	}

	@GetMapping("/certificates/{id}")
	public Certificate getCertificate(@PathVariable long id) {
		return certificateService.getById(id)
		                         .orElseThrow(() -> new EntityOperationException(
						                         "Certificate id was not found: " + id));
	}

	@PostMapping("/certificates")
	public Certificate addCertificate(@RequestBody Certificate certificate) {
		certificate.setId(0);
		return certificateService.save(certificate)
		                         .orElseThrow(() -> new EntityOperationException(
						                         "Certificate was not added"));
	}

	@PutMapping("/certificates")
	public Certificate updateCertificate(@RequestBody Certificate certificate) {
		return certificateService.update(certificate)
		                         .orElseThrow(() -> new EntityOperationException(
						                         "Certificate was not updated"));
	}

	@DeleteMapping("/certificates/{id}")
	public String removeCertificate(@PathVariable long id) {
		if (certificateService.getById(id).isEmpty()) {
			throw new EntityOperationException("Customer id was not found: " + id);
		}
		certificateService.remove(id);
		return "Certificate was deleted; id: " + id;
	}
}
