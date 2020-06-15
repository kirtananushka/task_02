package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.ParameterWrapper;
import com.epam.esm.repository.SearchRepository;
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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {EmbeddedTestConfig.class})
class SearchRepositoryImplTest {

	@Autowired
	private SearchRepository searchRepository;

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByName() {
		ParameterWrapper params = new ParameterWrapper();
		params.setName("porttitor");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(3L, result.get(0).getId());
		Assertions.assertEquals(4L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByNameSortByIdDesc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setName("porttitor");
		params.setSortBy("-certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(4L, result.get(0).getId());
		Assertions.assertEquals(3L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByDescription() {
		ParameterWrapper params = new ParameterWrapper();
		params.setDescription("dolor nunc");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(3L, result.get(0).getId());
		Assertions.assertEquals(5L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByPriceEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setPrice("30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(7L, result.get(0).getId());
		Assertions.assertEquals(8L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByPriceNotEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setPrice("<>30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(8, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByPriceNotEquals2() {
		ParameterWrapper params = new ParameterWrapper();
		params.setPrice("!=30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(8, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByPriceGreater() {
		ParameterWrapper params = new ParameterWrapper();
		params.setPrice(">30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(8, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByPriceLess() {
		ParameterWrapper params = new ParameterWrapper();
		params.setPrice("<40");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(7L, result.get(0).getId());
		Assertions.assertEquals(8L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByPriceGreaterOrEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setPrice(">=30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByPriceLessOrEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setPrice("<=30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(7L, result.get(0).getId());
		Assertions.assertEquals(8L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByPriceBetween() {
		ParameterWrapper params = new ParameterWrapper();
		params.setPrice("between 50 and 100");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(4, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(6L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByPriceNotBetween() {
		ParameterWrapper params = new ParameterWrapper();
		params.setPrice("not between 50 and 100");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(6, result.size());
		Assertions.assertEquals(2L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByDurationEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setDuration("30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(7L, result.get(0).getId());
		Assertions.assertEquals(9L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByDurationNotEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setDuration("<>30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(8, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByDurationNotEquals2() {
		ParameterWrapper params = new ParameterWrapper();
		params.setDuration("!=30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(8, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByDurationGreater() {
		ParameterWrapper params = new ParameterWrapper();
		params.setDuration(">30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(6, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByDurationLess() {
		ParameterWrapper params = new ParameterWrapper();
		params.setDuration("<40");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(4, result.size());
		Assertions.assertEquals(3L, result.get(0).getId());
		Assertions.assertEquals(9L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByDurationGreaterOrEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setDuration(">=30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(8, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByDurationLessOrEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setDuration("<=30");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(4, result.size());
		Assertions.assertEquals(3L, result.get(0).getId());
		Assertions.assertEquals(9L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByDurationBetween() {
		ParameterWrapper params = new ParameterWrapper();
		params.setDuration("between 50 and 100");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(4, result.size());
		Assertions.assertEquals(2L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByDurationNotBetween() {
		ParameterWrapper params = new ParameterWrapper();
		params.setDuration("not between 50 and 100");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(6, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(9L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByCreationDateEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setCreationDate("2020-03-01");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(1, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(1L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByCreationDateNotEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setCreationDate("<>2020-03-01");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(9, result.size());
		Assertions.assertEquals(2L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByCreationDateNotEquals2() {
		ParameterWrapper params = new ParameterWrapper();
		params.setCreationDate("!=2020-03-01");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(9, result.size());
		Assertions.assertEquals(2L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByCreationDateGreater() {
		ParameterWrapper params = new ParameterWrapper();
		params.setCreationDate(">2020-03-01");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(9, result.size());
		Assertions.assertEquals(2L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByCreationDateLess() {
		ParameterWrapper params = new ParameterWrapper();
		params.setCreationDate("<2020-03-05");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(1, result.size());
		Assertions.assertEquals(3L, result.get(0).getId());
		Assertions.assertEquals(3L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByCreationDateGreaterOrEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setCreationDate(">=2020-03-05");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(9, result.size());
		Assertions.assertEquals(2L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByCreationDateLessOrEquals() {
		ParameterWrapper params = new ParameterWrapper();
		params.setCreationDate("<=2020-03-05");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(3L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByCreationDateBetween() {
		ParameterWrapper params = new ParameterWrapper();
		params.setCreationDate("between 2020-03-05 and 2020-05-25");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(9, result.size());
		Assertions.assertEquals(2L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByCreationDateNotBetween() {
		ParameterWrapper params = new ParameterWrapper();
		params.setCreationDate("not between 2020-03-05 and 2020-05-25");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(1, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(1L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByAllParams() {
		ParameterWrapper params = new ParameterWrapper();
		params.setName("Maecenas consectetuer");
		params.setDescription("Maecenas consectetuer");
		params.setPrice("150");
		params.setCreationDate("2020-05-25");
		params.setDuration("50");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(1, result.size());
		Assertions.assertEquals(2L, result.get(0).getId());
		Assertions.assertEquals(2L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void filterByTag() {
		ParameterWrapper params = new ParameterWrapper();
		params.setTag("Lorem");
		params.setSortBy("certificates.id");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(3L, result.get(0).getId());
		Assertions.assertEquals(5L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByName() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("name");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(8L, result.get(0).getId());
		Assertions.assertEquals(1L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByDescription() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("description");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(8L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByPrice() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("price");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(8L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByCreationDate() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("creation_date");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(2L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByDuration() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("duration");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(3L, result.get(0).getId());
		Assertions.assertEquals(1L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByNameAsc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("+name");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(8L, result.get(0).getId());
		Assertions.assertEquals(1L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByDescriptionAsc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("+description");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(8L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByPriceAsc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("+price");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(8L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByCreationDateAsc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("+creation_date");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(2L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByDurationAsc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("+duration");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(3L, result.get(0).getId());
		Assertions.assertEquals(1L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByNameDesc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("-name");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(8L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByDescriptionDesc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("-description");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(10L, result.get(0).getId());
		Assertions.assertEquals(8L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByPriceDesc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("-price");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(10L, result.get(0).getId());
		Assertions.assertEquals(7L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByCreationDateDesc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("-creation_date");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(2L, result.get(0).getId());
		Assertions.assertEquals(1L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByDurationDesc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("-duration");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(3L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByAllParams() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("name,description,price,creation_date,modification_date,duration");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(8L, result.get(0).getId());
		Assertions.assertEquals(1L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByAllParamsAsc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("+name,+description,+price,+creation_date,+modification_date,+duration");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(8L, result.get(0).getId());
		Assertions.assertEquals(1L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByAllParamsDesc() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("-name,-description,-price,-creation_date,-modification_date,-duration");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(1L, result.get(0).getId());
		Assertions.assertEquals(8L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByAllParamsMixed() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("name,+description,-price,creation_date,-modification_date,+duration");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(8L, result.get(0).getId());
		Assertions.assertEquals(1L, result.get(result.size() - 1).getId());
	}

	@Test
	@Sql("/db.test/v1_0__initial_test_schema_creation.sql")
	@Sql("/db.test/test_inserts.sql")
	void sortByAllParamsMixed2() {
		ParameterWrapper params = new ParameterWrapper();
		params.setSortBy("+price,-name,+description,-creation_date,modification_date,-duration");
		List<Certificate> result = new ArrayList<>(searchRepository.search(params));
		Assertions.assertEquals(10, result.size());
		Assertions.assertEquals(7L, result.get(0).getId());
		Assertions.assertEquals(10L, result.get(result.size() - 1).getId());
	}
}