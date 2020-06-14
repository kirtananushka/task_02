package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.EmbeddedTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {EmbeddedTestConfig.class})
public class CertificateRepositoryImplTest {

	private static final Certificate certificate;

	static {
		certificate = new Certificate();
		certificate.setId(1L);
		certificate.setName("Test lorem ipsum");
		certificate.setDescription("Lorem ipsum dolor sit amet elit");
		certificate.setPrice(new BigDecimal(70));
		certificate.setCreationDate(LocalDate.of(2020, 3, 1));
		certificate.setDuration(270);
		List<Tag> tags = new ArrayList<>();
		Tag tag1 = new Tag();
		tag1.setId(21L);
		tag1.setName("Purus");
		tags.add(tag1);
		Tag tag2 = new Tag();
		tag2.setId(24L);
		tag2.setName("Eleifend");
		tags.add(tag2);
		certificate.setTags(tags);
	}

	@Autowired
	private CertificateRepository certificateRepository;

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void getAll() {
		Assertions.assertEquals(10, certificateRepository.getAll().size());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void getById() {
		Optional<Certificate> testCertificate = certificateRepository.getById(1L);
		Assertions.assertNotNull(testCertificate);
		Assertions.assertEquals(certificate, testCertificate.get());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void save() {
		Optional<Certificate> testCertificate = certificateRepository.save(certificate);
		Assertions.assertNotNull(testCertificate);
		Assertions.assertEquals(certificate, testCertificate.get());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void remove() {
		Optional<Certificate> testCertificate = certificateRepository.getById(1L);
		Assertions.assertEquals(10, certificateRepository.getAll().size());
		certificateRepository.remove(testCertificate.get());
		Assertions.assertEquals(9, certificateRepository.getAll().size());
		Assertions.assertThrows(NullPointerException.class,
						() -> certificateRepository.getById(1L).get());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void update() {
		Certificate certificate = certificateRepository.getById(1L).get();
		certificate.setModificationDate(LocalDate.now());
		Assertions.assertEquals(certificate, certificateRepository.update(certificate).get());
	}
}