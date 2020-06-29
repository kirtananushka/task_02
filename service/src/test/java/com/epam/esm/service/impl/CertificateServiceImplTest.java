package com.epam.esm.service.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.dto.TagDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CertificateServiceImplTest {

	private static Tag tag;
	private static TagDto tagDto;
	private static Certificate certificateFirst;
	private static Certificate certificateSecond;
	private static CertificateDto certificateFirstDto;
	private static CertificateDto certificateSecondDto;
	private static List<Tag> tagList;
	private static List<TagDto> tagListDto;
	private CertificateService certificateService;
	@Mock
	private CertificateRepository certificateRepository;
	@Mock
	private TagService tagService;

	@BeforeEach
	void setup() {
		certificateService = new CertificateServiceImpl(certificateRepository, tagService);
		tag = new Tag();
		tag.setId(1L);
		tag.setName("First tag");
		tagDto = new TagDto();
		tagDto.setId(1L);
		tagDto.setName("First tag");
		tagList = new ArrayList<>();
		tagListDto = new ArrayList<>();
		certificateFirst = new Certificate();
		certificateFirst.setId(1L);
		certificateFirst.setName("First certificate");
		certificateFirst.setDescription("First certificate");
		certificateFirst.setPrice(new BigDecimal(100));
		certificateFirst.setDuration(100);
		certificateFirst.setTags(tagList);
		certificateFirstDto = new CertificateDto();
		certificateFirstDto.setId(1L);
		certificateFirstDto.setName("First certificate");
		certificateFirstDto.setDescription("First certificate");
		certificateFirstDto.setPrice(new BigDecimal(100));
		certificateFirstDto.setDuration(100);
		certificateFirstDto.setTags(tagListDto);
		certificateSecond = new Certificate();
		certificateSecond.setId(2L);
		certificateSecond.setName("Second certificate");
		certificateSecond.setDescription("Second certificate");
		certificateSecond.setPrice(new BigDecimal(200));
		certificateSecond.setDuration(200);
		certificateSecond.setTags(tagList);
		certificateSecondDto = new CertificateDto();
		certificateSecondDto.setId(2L);
		certificateSecondDto.setName("Second certificate");
		certificateSecondDto.setDescription("Second certificate");
		certificateSecondDto.setPrice(new BigDecimal(200));
		certificateSecondDto.setDuration(200);
		certificateSecondDto.setTags(tagListDto);
	}

	@Test
	void getById() {
		when(certificateRepository.getById(anyLong())).thenReturn(Optional.of(certificateFirst));
		CertificateDto certificateDto = certificateService.getById(certificateFirst.getId()).get();
		Assertions.assertEquals(certificateFirst.getId(), certificateDto.getId());
		Assertions.assertEquals(certificateFirst.getName(), certificateDto.getName());
	}

	@Test
	void getByInvalidId() {
		Assertions.assertThrows(Exception.class, () -> certificateService.getById(-3L));
	}

	@Test
	void getByIncorrectId() {
		when(certificateRepository.getById(anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(Exception.class, () -> certificateService.getById(3L));
	}

	@Test
	void getByIdGetWithException() {
		when(certificateRepository.getById(anyLong())).thenThrow(RuntimeException.class);
		Assertions.assertThrows(Exception.class, () -> certificateService.getById(3L));
	}

	@Test
	void saveWithException() {
		Assertions.assertThrows(Exception.class, () -> certificateService.save(certificateSecondDto));
	}

	@Test
	void save() {
		when(certificateRepository.save(any())).thenReturn(Optional.of(certificateSecond));
		Assertions.assertNotNull(certificateService.save(certificateSecondDto).get());
	}

	@Test
	void update() {
		when(certificateRepository.update(any())).thenReturn(Optional.of(certificateSecond));
		Assertions.assertNotNull(certificateService.update(certificateSecondDto).get());
	}

	@Test
	void saveWithIncorrectName() {
		certificateFirstDto.setName(Stream.generate(() -> "a").limit(65).collect(Collectors.joining()));
		Assertions.assertThrows(Exception.class, () -> certificateService.save(certificateFirstDto));
	}

	@Test
	void saveWithIncorrectDescription() {
		certificateFirstDto.setName(Stream.generate(() -> "a").limit(65).collect(Collectors.joining()));
		Assertions.assertThrows(Exception.class, () -> certificateService.save(certificateFirstDto));
	}

	@Test
	void saveWithIncorrectPrice() {
		certificateFirstDto.setPrice(new BigDecimal(-1));
		Assertions.assertThrows(Exception.class, () -> certificateService.save(certificateFirstDto));
	}

	@Test
	void saveWithIncorrectDuration() {
		certificateFirstDto.setDuration(-1);
		Assertions.assertThrows(Exception.class, () -> certificateService.save(certificateFirstDto));
	}

	@Test
	void saveWithIncorrectTagId() {
		tagDto.setId(-1L);
		tagListDto.add(tagDto);
		certificateFirstDto.setTags(tagListDto);
		Assertions.assertThrows(Exception.class, () -> certificateService.save(certificateFirstDto));
	}

	@Test
	void updateWithException() {
		Assertions.assertThrows(Exception.class, () -> certificateService.update(certificateSecondDto));
	}

	@Test
	void updateWithIncorrectName() {
		certificateFirstDto.setName(Stream.generate(() -> "a").limit(65).collect(Collectors.joining()));
		Assertions.assertThrows(Exception.class, () -> certificateService.update(certificateFirstDto));
	}

	@Test
	void updateWithIncorrectDescription() {
		certificateFirstDto.setName(Stream.generate(() -> "a").limit(65).collect(Collectors.joining()));
		Assertions.assertThrows(Exception.class, () -> certificateService.update(certificateFirstDto));
	}

	@Test
	void updateWithIncorrectPrice() {
		certificateFirstDto.setPrice(new BigDecimal(-1));
		Assertions.assertThrows(Exception.class, () -> certificateService.update(certificateFirstDto));
	}

	@Test
	void updateWithIncorrectDuration() {
		certificateFirstDto.setDuration(-1);
		Assertions.assertThrows(Exception.class, () -> certificateService.update(certificateFirstDto));
	}

	@Test
	void updateWithIncorrectTagId() {
		tagDto.setId(-1L);
		tagListDto.add(tagDto);
		certificateFirstDto.setTags(tagListDto);
		Assertions.assertThrows(Exception.class, () -> certificateService.update(certificateFirstDto));
	}

	@Test
	void removeIncorrectId() {
		certificateFirst.setId(-1L);
		Assertions.assertThrows(Exception.class, () -> certificateService.remove(certificateFirst.getId()));
	}
}