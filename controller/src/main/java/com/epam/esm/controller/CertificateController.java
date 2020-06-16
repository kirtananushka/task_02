package com.epam.esm.controller;

import com.epam.esm.service.CertificateService;
import com.epam.esm.service.dto.CertificateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CertificateController {

	private final CertificateService certificateService;

	@GetMapping("/certificates/{certificate_id}")
	public CertificateDTO getCertificate(@PathVariable Long certificate_id) {
		return certificateService.getById(certificate_id).get();
	}

	@PostMapping("/certificates")
	@ResponseStatus(HttpStatus.CREATED)
	public CertificateDTO addCertificate(@RequestBody CertificateDTO certificateDTO) {
		certificateDTO.setId(0L);
		certificateDTO = certificateService.save(certificateDTO).get();
		return certificateDTO;
	}

	@PutMapping("/certificates")
	public CertificateDTO updateCertificate(@RequestBody CertificateDTO certificateDTO) {
		certificateDTO = certificateService.update(certificateDTO).get();
		return certificateDTO;
	}

	@DeleteMapping("/certificates/{certificate_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeCertificate(@PathVariable Long certificate_id) {
		certificateService.remove(certificate_id);
	}
}
