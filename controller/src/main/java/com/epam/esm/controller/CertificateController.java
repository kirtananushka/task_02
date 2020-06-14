package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.EntityOperationException;
import com.epam.esm.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CertificateController {

	private final CertificateService certificateService;

	@GetMapping("/certificates/{certificate_id}")
	public Certificate getCertificate(@PathVariable Long certificate_id) {
		return certificateService.getById(certificate_id)
		                         .orElseThrow(EntityOperationException::new);
	}

	@PostMapping("/certificates")
	public Certificate addCertificate(@RequestBody Certificate certificate,
	                                  HttpServletResponse response) {
		certificate.setId(0L);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return certificateService.save(certificate)
		                         .orElseThrow(EntityOperationException::new);
	}

	@PutMapping("/certificates")
	public Certificate updateCertificate(@RequestBody Certificate certificate) {
		return certificateService.update(certificate)
		                         .orElseThrow(EntityOperationException::new);
	}

	@DeleteMapping("/certificates/{certificate_id}")
	public void removeCertificate(@PathVariable Long certificate_id, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		if (certificateService.getById(certificate_id).isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		certificateService.remove(certificate_id);
	}
}
