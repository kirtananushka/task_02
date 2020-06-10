package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.config.EmbeddedTestConfig;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {EmbeddedTestConfig.class})
public class CertificateRepositoryImplTest {

	@Autowired
	CertificateRepository certificateRepository;

	@Test
	public void getById() {
		Optional<Certificate> byId = certificateRepository.getById(1L);
	}
}