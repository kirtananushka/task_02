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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
		when(certificateRepository.getById(certificateFirst.getId()))
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
		when(certificateRepository.getById(anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(ServiceException.class, () -> certificateService.getById(3L));
	}

	@Test
	void getByIdGetWithException() {
		when(certificateRepository.getById(anyLong())).thenThrow(RuntimeException.class);
		Assertions.assertThrows(ServiceException.class, () -> certificateService.getById(3L));
	}

	@Test
	void saveWithException() {
		when(certificateRepository.update(certificateSecond)).thenReturn(null);
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.save(certificateSecondDTO));
	}

	@Test
	void saveWithIncorrectName() {
		certificateFirstDTO.setName(
						"ToooooooooooooooooooooooooooooooooooooooooLoooooooooooooooooooooooong");
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.save(certificateFirstDTO));
	}

	@Test
	void saveWithIncorrectDescription() {
		certificateFirstDTO.setDescription(
						"ToooooooooooooooooooooooooooooooooooooooooLoooooooooooooooooooooooong");
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.save(certificateFirstDTO));
	}

	@Test
	void saveWithIncorrectPrice() {
		certificateFirstDTO.setPrice(new BigDecimal(-1));
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.save(certificateFirstDTO));
	}

	@Test
	void saveWithIncorrectDuration() {
		certificateFirstDTO.setDuration(-1);
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.save(certificateFirstDTO));
	}

	@Test
	void saveWithIncorrectTagId() {
		tagDTO.setId(-1L);
		tagListDTO.add(tagDTO);
		certificateFirstDTO.setTags(tagListDTO);
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.save(certificateFirstDTO));
	}

	@Test
	void updateWithException() {
		when(certificateRepository.update(certificateSecond)).thenReturn(null);
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.update(certificateSecondDTO));
	}

	@Test
	void updateWithIncorrectName() {
		certificateFirstDTO.setName(
						"ToooooooooooooooooooooooooooooooooooooooooLoooooooooooooooooooooooong");
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.update(certificateFirstDTO));
	}

	@Test
	void updateWithIncorrectDescription() {
		certificateFirstDTO.setDescription(
						"ToooooooooooooooooooooooooooooooooooooooooLoooooooooooooooooooooooong");
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.update(certificateFirstDTO));
	}

	@Test
	void updateWithIncorrectPrice() {
		certificateFirstDTO.setPrice(new BigDecimal(-1));
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.update(certificateFirstDTO));
	}

	@Test
	void updateWithIncorrectDuration() {
		certificateFirstDTO.setDuration(-1);
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.update(certificateFirstDTO));
	}

	@Test
	void updateWithIncorrectTagId() {
		tagDTO.setId(-1L);
		tagListDTO.add(tagDTO);
		certificateFirstDTO.setTags(tagListDTO);
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.update(certificateFirstDTO));
	}

	@Test
	void save() {
		when(certificateRepository.save(certificateSecond)).thenReturn(Optional.of
						(certificateSecond));
		Assertions.assertNotNull(certificateService.save(certificateSecondDTO).get());
	}
	//	@Test
//	void update() {
//		when(certificateRepository.update(certificateSecond)).thenReturn(Optional.of
//		(certificateSecond));
//		Assertions.assertNotNull(certificateService.save(certificateSecondDTO).get());
//	}

	@Test
	void removeIncorrectId() {
		certificateFirst.setId(-1L);
		Assertions.assertThrows(ServiceException.class,
						() -> certificateService.remove(certificateFirst.getId()));
	}
}