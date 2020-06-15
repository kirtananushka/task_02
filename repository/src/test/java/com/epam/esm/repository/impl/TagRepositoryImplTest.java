package com.epam.esm.repository.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.config.EmbeddedTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {EmbeddedTestConfig.class})
class TagRepositoryImplTest {

	private static final Tag tag;

	static {
		tag = new Tag();
		tag.setId(21L);
		tag.setName("Purus");
	}

	@Autowired
	private TagRepositoryImpl tagRepository;

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void getAll() {
		tagRepository.getAll();
		Assertions.assertEquals(30, tagRepository.getAll().size());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void getById() {
		Assertions.assertNotNull(tagRepository.getById(21L).get());
		Assertions.assertEquals(tag, tagRepository.getById(21L).get());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void save() {
		Tag tag = new Tag();
		tag.setId(31L);
		tag.setName("NewTag");
		Optional<Tag> testTag = tagRepository.save(tag);
		Assertions.assertNotNull(testTag);
		Assertions.assertEquals(tag, testTag.get());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void remove() {
		Optional<Tag> testTag = tagRepository.getById(1L);
		Assertions.assertEquals(30, tagRepository.getAll().size());
		tagRepository.remove(testTag.get());
		Assertions.assertEquals(29, tagRepository.getAll().size());
		Assertions.assertThrows(NullPointerException.class,
						() -> tagRepository.getById(1L).get());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void getTagsByCertificateId() {
		Assertions.assertEquals(21L, tagRepository.getTagsByCertificateId(1L).get(0).getId());
	}
}