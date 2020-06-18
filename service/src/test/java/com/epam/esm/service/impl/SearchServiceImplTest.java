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
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceDecimal() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("10.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceLongDecimal() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("10000000.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchNegativePrice() {
		params.setPrice("-10");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchInvalidPrice() {
		params.setPrice("1g0");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchInvalidDecimalPrice() {
		params.setPrice("10.000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchLongDecimalPrice() {
		params.setPrice("100000000.00");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchLongPrice() {
		params.setPrice("100000000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchCreationDate() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setCreationDate("2020-01-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchInvalidCreationDate() {
		params.setCreationDate("202-01-01");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchModificationDate() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setModificationDate("2020-01-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchInvalidModificationDate() {
		params.setModificationDate("202-01-01");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchDuration() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setDuration("10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchNegativeDuration() {
		params.setDuration("-10");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchInvalidDuration() {
		params.setDuration("1g0");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchLongDuration() {
		params.setDuration("100000000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchTagId() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setTagId("10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchNegativeTagId() {
		params.setTagId("-10");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchInvalidTagId() {
		params.setTagId("1g0");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchLongTagId() {
		params.setTagId("100000000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchPage() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPage("10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchNegativePage() {
		params.setPage("-10");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchInvalidPage() {
		params.setPage("1g0");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchLongPage() {
		params.setPage("100000000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchPerPage() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSize("10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchNegativePerPage() {
		params.setSize("-10");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchInvalidPerPage() {
		params.setSize("1g0");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchLongPerPage() {
		params.setSize("100000000");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchSortCertificatesId() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("certificates.id");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortName() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("name");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortDescription() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("description");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortPrice() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("price");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortCreationDate() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("creation_date");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortModificationDate() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("modification_date");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortDuration() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("duration");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortAll() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy(
						"certificates.id,name,description,price,creation_date,modification_date,duration");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortDescCertificatesId() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("-certificates.id");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortDescName() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("-name");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortDescDescription() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("-description");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortDescPrice() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("-price");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortDescCreationDate() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("-creation_date");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortDescModificationDate() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("-modification_date");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortDescDuration() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy("-duration");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortDescAll() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setSortBy(
						"-certificates.id,-name,-description,-price,-creation_date,-modification_date,"
										+ "-duration");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchSortIncorrectParam() {
		params.setSortBy("-blablabla");
		Assertions.assertThrows(ServiceException.class, () -> searchService.searchCertificate(params));
	}

	@Test
	void searchPriceGreater() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice(">10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceDecimalGreater() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice(">10.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceLongDecimalGreater() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice(">10000000.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchCreationDateGreater() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setCreationDate(">2020-01-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchModificationDateGreater() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setModificationDate(">2020-01-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchDurationGreater() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setDuration(">10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchTagIdGreater() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setTagId(">10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("<10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceDecimalLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("<10.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceLongDecimalLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("<10000000.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchCreationDateLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setCreationDate("<2020-01-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchModificationDateLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setModificationDate("<2020-01-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchDurationLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setDuration("<10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchTagIdLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setTagId("<10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceGreaterLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("<>10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceDecimalGreaterLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("<>10.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceLongDecimalGreaterLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("<>10000000.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchCreationDateGreaterLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setCreationDate("<>2020-01-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchModificationDateGreaterLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setModificationDate("<>2020-01-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchDurationGreaterLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setDuration("<>10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchTagIdGreaterLess() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setTagId("<>10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceNonEquals() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("!=10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceDecimalNonEquals() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("!=10.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceLongDecimalNonEquals() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("!=10000000.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchCreationDateNonEquals() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setCreationDate("!=2020-01-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchModificationDateNonEquals() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setModificationDate("!=2020-01-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchDurationNonEquals() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setDuration("!=10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchTagIdNonEquals() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setTagId("!=10");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("between 10 and 20");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceDecimalBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("between 10.00 and 20.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceLongDecimalBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("between 10000000.00 and 20000000.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchCreationDateBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setCreationDate("between 2020-01-01 and 2020-02-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchModificationDateBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setModificationDate("between 2020-01-01 and 2020-02-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchDurationBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setDuration("between 10 and 20");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchTagIdBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setTagId("between 10 and 20");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceNotBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("not between 10 and 20");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceDecimalNotBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("not between 10.00 and 20.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchPriceLongDecimalNotBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setPrice("not between 10000000.00 and 20000000.00");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchCreationDateNotBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setCreationDate("not between 2020-01-01 and 2020-02-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchModificationDateNotBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setModificationDate("not between 2020-01-01 and 2020-02-01");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchDurationNotBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setDuration("not between 10 and 20");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}

	@Test
	void searchTagIdNotBetween() {
		when(searchRepository.searchCertificate(any())).thenReturn(certificateList);
		params.setTagId("not between 10 and 20");
		Assertions.assertNotNull(searchService.searchCertificate(params));
	}
}