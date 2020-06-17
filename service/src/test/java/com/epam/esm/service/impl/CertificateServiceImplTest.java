package com.epam.esm.service.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.config.ServiceTestConfig;
import com.epam.esm.service.dto.CertificateDTO;
import com.epam.esm.service.dto.TagDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
class CertificateServiceImplTest {

	private static Tag tag;
	private static TagDTO tagDTO;
	private static Certificate certificateFirst;
	private static Certificate certificateSecond;
	private static CertificateDTO certificateFirstDTO;
	private static CertificateDTO certificateSecondDTO;
	private static List<Certificate> certificateList;
	private static List<CertificateDTO> certificateListDTO;
	private static List<Tag> tagList;
	private static List<TagDTO> tagListDTO;
	@Autowired
	private CertificateRepository certificateRepository;
	@Autowired
	private CertificateService certificateService;

	@BeforeEach
	void setup() {
		tag = new Tag();
		tag.setId(1L);
		tag.setName("First tag");
		tagDTO = new TagDTO();
		tagDTO.setId(1L);
		tagDTO.setName("First tag");
		tagList = new ArrayList<>();
		tagListDTO = new ArrayList<>();
		certificateFirst = new Certificate();
		certificateFirst.setId(1L);
		certificateFirst.setName("First certificate");
		certificateFirst.setDescription("First certificate");
		certificateFirst.setPrice(new BigDecimal(100));
		certificateFirst.setDuration(100);
		certificateFirst.setTags(tagList);
		certificateFirstDTO = new CertificateDTO();
		certificateFirstDTO.setId(1L);
		certificateFirstDTO.setName("First certificate");
		certificateFirstDTO.setDescription("First certificate");
		certificateFirstDTO.setPrice(new BigDecimal(100));
		certificateFirstDTO.setDuration(100);
		certificateFirstDTO.setTags(tagListDTO);
		certificateSecond = new Certificate();
		certificateSecond.setId(2L);
		certificateSecond.setName("Second certificate");
		certificateSecond.setDescription("Second certificate");
		certificateSecond.setPrice(new BigDecimal(200));
		certificateSecond.setDuration(200);
		certificateSecond.setTags(tagList);
		certificateSecondDTO = new CertificateDTO();
		certificateSecondDTO.setId(2L);
		certificateSecondDTO.setName("Second certificate");
		certificateSecondDTO.setDescription("Second certificate");
		certificateSecondDTO.setPrice(new BigDecimal(200));
		certificateSecondDTO.setDuration(200);
		certificateSecondDTO.setTags(tagListDTO);
	}

	@Test
	void getById() {
		Mockito.when(certificateRepository.getById(certificateFirst.getId()))
		       .thenReturn(Optional.of(certificateFirst));
		Optional<CertificateDTO> optionalCertificateDTO =
						certificateService.getById(certificateFirst.getId());
		CertificateDTO certificateDTO = optionalCertificateDTO.get();
		Assertions.assertEquals(certificateFirst.getId(), certificateDTO.getId());
		Assertions.assertEquals(certificateFirst.getName(), certificateDTO.getName());
	}

	@Test
	void getByInvalidId() {
		Assertions.assertThrows(ServiceException.class, () -> certificateService.getById(-3L));
	}

	@Test
	void getByIncorrectId() {
		Mockito.when(certificateRepository.getById(3L)).thenReturn(Optional.empty());
		Assertions.assertThrows(ServiceException.class, () -> certificateService.getById(3L));
	}

	@Test
	void getByIdGetWithException() {
		Mockito.when(certificateRepository.getById(3L)).thenThrow(RuntimeException.class);
		Assertions.assertThrows(ServiceException.class, () -> certificateService.getById(3L));
	}

	@Test
	void save() {
	}

	@Test
	void update() {
	}

	@Test
	void remove() {
	}
}