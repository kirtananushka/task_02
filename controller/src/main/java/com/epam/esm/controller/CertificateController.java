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

/**
 * Class CertificateController for task 2.
 *
 * @author KIR TANANUSHKA
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificates")
public class CertificateController {

	/**
	 * Field certificateService
	 */
	private final CertificateService certificateService;

	/**
	 * GET method getCertificate returns CertificateDTO object by ID.
	 * <p>
	 * [GET /api/v1/certificates/{id}]
	 * Parameters: certificate id (Long, required) - unique certificate ID.
	 * Request (application/json).
	 * Response 200 (application/json).
	 * <p>
	 * Example json:
	 * {
	 * "id": 1,
	 * "name": "Lorem ipsum",
	 * "description": "Lorem ipsum dolor sit amet elit",
	 * "price": 70.00,
	 * "creationDate": "2020-03-01",
	 * "modificationDate": "2020-06-18",
	 * "duration": 270,
	 * "tags": [
	 * {
	 * "id": 21,
	 * "name": "Purus"
	 * },
	 * {
	 * "id": 24,
	 * "name": "Eleifend"
	 * }
	 * ]
	 * }
	 *
	 * @param id of type Long.
	 * @return CertificateDTO.
	 */
	@GetMapping("/{id}")
	public CertificateDTO getCertificate(@PathVariable Long id) {
		return certificateService.getById(id).get();
	}

	/**
	 * POST method addCertificate adds CertificateDTO object.
	 * <p>
	 * [POST /api/v1/certificates/]
	 * Parameters: certificate (CertificateDTO, required).
	 * Request (application/json).
	 * Response 200 (application/json).
	 * <p>
	 * Example json:
	 * {
	 * "name": "Lorem ipsum",
	 * "description": "Lorem ipsum dolor sit amet elit",
	 * "price": 70.00,
	 * "duration": 270,
	 * "tags": [
	 * {
	 * "id": 21,
	 * "name": "Purus"
	 * },
	 * {
	 * "id": 24,
	 * "name": "Eleifend"
	 * }
	 * ]
	 * }
	 * <p>
	 * The length of fields "name", "description": up to 64 characters.
	 * Price format: 12345678.90 or 12345678 (precision of 8 and a scale of 2).
	 * Duration format: 12345678.
	 * To assign an existing tag, you can specify ID or ID and name.
	 * To assign a new tag, both ID and a name are required.
	 *
	 * @param certificateDTO of type CertificateDTO.
	 * @return CertificateDTO.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CertificateDTO addCertificate(@RequestBody CertificateDTO certificateDTO) {
		certificateDTO.setId(0L);
		certificateDTO = certificateService.save(certificateDTO).get();
		return certificateDTO;
	}

	/**
	 * PUT method updateCertificate updates CertificateDTO object.
	 * <p>
	 * [PUT /api/v1/certificates/]
	 * Parameters: certificate (CertificateDTO, required).
	 * Request (application/json).
	 * Response 200 (application/json).
	 * <p>
	 * Example json:
	 * {
	 * "id": 1,
	 * "name": "Lorem ipsum",
	 * "description": "Lorem ipsum dolor sit amet elit",
	 * "price": 70.00,
	 * "duration": 270,
	 * "tags": [
	 * {
	 * "id": 21,
	 * "name": "Purus"
	 * },
	 * {
	 * "id": 24,
	 * "name": "Eleifend"
	 * }
	 * ]
	 * }
	 * <p>
	 * The length of fields "name", "description": up to 64 characters.
	 * Price format: 12345678.90 or 12345678 (precision of 8 and a scale of 2).
	 * Duration format: 12345678.
	 * To assign an existing tag, you can specify ID or ID and name.
	 * To assign a new tag, both ID and a name are required.
	 *
	 * @param certificateDTO of type CertificateDTO.
	 * @return CertificateDTO.
	 */
	@PutMapping
	public CertificateDTO updateCertificate(@RequestBody CertificateDTO certificateDTO) {
		certificateDTO = certificateService.update(certificateDTO).get();
		return certificateDTO;
	}

	/**
	 * Method removeCertificate removes CertificateDTO object by ID.
	 * <p>
	 * [DELETE /api/v1/certificates/{id}]
	 * Parameters: certificate id (Long, required) - unique certificate ID.
	 * Request (application/json).
	 * Response 204 (application/json).
	 *
	 * @param id of type Long.
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeCertificate(@PathVariable Long id) {
		certificateService.remove(id);
	}
}
