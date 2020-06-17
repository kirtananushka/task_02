package com.epam.esm.service.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.parameterwrapper.ParameterWrapper;
import com.epam.esm.repository.SearchRepository;
import com.epam.esm.service.SearchService;
import com.epam.esm.service.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceImplTest {

	private static ParameterWrapper params;
	private static List<Certificate> certificateList;
	private SearchService searchService;
	@Mock
	private SearchRepository searchRepository;

	@BeforeEach
	void setup() {
		searchService = new SearchServiceImpl(searchRepository);
		params = new ParameterWrapper();
		certificateList = new ArrayList<>();
	}

	@Test
	void searchPrice() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceDecimal() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("10.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceLongDecimal() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("10000000.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchNegativePrice() {
		params.setPrice("-10");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchInvalidPrice() {
		params.setPrice("1g0");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchInvalidDecimalPrice() {
		params.setPrice("10.000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchLongDecimalPrice() {
		params.setPrice("100000000.00");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchLongPrice() {
		params.setPrice("100000000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchCreationDate() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setCreationDate("2020-01-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchInvalidCreationDate() {
		params.setCreationDate("202-01-01");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchModificationDate() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setModificationDate("2020-01-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchInvalidModificationDate() {
		params.setModificationDate("202-01-01");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchDuration() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setDuration("10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchNegativeDuration() {
		params.setDuration("-10");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchInvalidDuration() {
		params.setDuration("1g0");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchLongDuration() {
		params.setDuration("100000000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchTagId() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setTagId("10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchNegativeTagId() {
		params.setTagId("-10");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchInvalidTagId() {
		params.setTagId("1g0");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchLongTagId() {
		params.setTagId("100000000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchPage() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPage("10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchNegativePage() {
		params.setPage("-10");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchInvalidPage() {
		params.setPage("1g0");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchLongPage() {
		params.setPage("100000000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchPerPage() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPerPage("10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchNegativePerPage() {
		params.setPerPage("-10");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchInvalidPerPage() {
		params.setPerPage("1g0");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchLongPerPage() {
		params.setPerPage("100000000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchSortCertificatesId() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("certificates.id");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortName() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("name");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortDescription() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("description");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortPrice() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("price");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortCreationDate() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("creation_date");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortModificationDate() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("modification_date");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortDuration() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("duration");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortAll() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy(
						"certificates.id,name,description,price,creation_date,modification_date,duration");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortDescCertificatesId() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("-certificates.id");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortDescName() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("-name");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortDescDescription() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("-description");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortDescPrice() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("-price");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortDescCreationDate() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("-creation_date");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortDescModificationDate() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("-modification_date");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortDescDuration() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy("-duration");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortDescAll() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setSortBy(
						"-certificates.id,-name,-description,-price,-creation_date,-modification_date,"
										+ "-duration");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchSortIncorrectParam() {
		params.setSortBy("-blablabla");
		Assertions.assertThrows(ServiceException.class, () -> searchService.search(params));
	}

	@Test
	void searchPriceGreater() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice(">10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceDecimalGreater() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice(">10.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceLongDecimalGreater() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice(">10000000.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchCreationDateGreater() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setCreationDate(">2020-01-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchModificationDateGreater() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setModificationDate(">2020-01-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchDurationGreater() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setDuration(">10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchTagIdGreater() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setTagId(">10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("<10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceDecimalLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("<10.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceLongDecimalLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("<10000000.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchCreationDateLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setCreationDate("<2020-01-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchModificationDateLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setModificationDate("<2020-01-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchDurationLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setDuration("<10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchTagIdLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setTagId("<10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceGreaterLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("<>10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceDecimalGreaterLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("<>10.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceLongDecimalGreaterLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("<>10000000.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchCreationDateGreaterLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setCreationDate("<>2020-01-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchModificationDateGreaterLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setModificationDate("<>2020-01-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchDurationGreaterLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setDuration("<>10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchTagIdGreaterLess() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setTagId("<>10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceNonEquals() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("!=10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceDecimalNonEquals() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("!=10.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceLongDecimalNonEquals() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("!=10000000.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchCreationDateNonEquals() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setCreationDate("!=2020-01-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchModificationDateNonEquals() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setModificationDate("!=2020-01-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchDurationNonEquals() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setDuration("!=10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchTagIdNonEquals() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setTagId("!=10");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("between 10 and 20");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceDecimalBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("between 10.00 and 20.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceLongDecimalBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("between 10000000.00 and 20000000.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchCreationDateBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setCreationDate("between 2020-01-01 and 2020-02-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchModificationDateBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setModificationDate("between 2020-01-01 and 2020-02-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchDurationBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setDuration("between 10 and 20");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchTagIdBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setTagId("between 10 and 20");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceNotBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("not between 10 and 20");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceDecimalNotBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("not between 10.00 and 20.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchPriceLongDecimalNotBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setPrice("not between 10000000.00 and 20000000.00");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchCreationDateNotBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setCreationDate("not between 2020-01-01 and 2020-02-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchModificationDateNotBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setModificationDate("not between 2020-01-01 and 2020-02-01");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchDurationNotBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setDuration("not between 10 and 20");
		Assertions.assertNotNull(searchService.search(params));
	}

	@Test
	void searchTagIdNotBetween() {
		when(searchRepository.search(any())).thenReturn(certificateList);
		params.setTagId("not between 10 and 20");
		Assertions.assertNotNull(searchService.search(params));
	}
}