package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.ServiceNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.TagDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

	private static Tag tagFirst;
	private static Tag tagSecond;
	private static TagDto tagFirstDto;
	private static TagDto tagSecondDto;
	private static List<Tag> tagList;
	@Mock
	private TagRepository tagRepository;
	@Mock
	private TagService tagService;

	@BeforeEach
	void setup() {
		tagService = new TagServiceImpl(tagRepository);
		tagFirst = new Tag();
		tagFirst.setId(1L);
		tagFirst.setName("First tag");
		tagSecond = new Tag();
		tagSecond.setId(2L);
		tagSecond.setName("Second tag");
		tagList = new ArrayList<>();
		tagList.add(tagFirst);
		tagList.add(tagSecond);
		tagFirstDto = new TagDto();
		tagFirstDto.setId(1L);
		tagFirstDto.setName("First tag");
		tagSecondDto = new TagDto();
		tagSecondDto.setId(2L);
		tagSecondDto.setName("Second tag");
	}

	@Test
	void getById() {
		when(tagRepository.getById(anyLong())).thenReturn(Optional.of(tagFirst));
		Optional<TagDto> optionalTagDto = tagService.getById(tagFirst.getId());
		TagDto tagDto = optionalTagDto.get();
		Assertions.assertEquals(tagFirst.getId(), tagDto.getId());
		Assertions.assertEquals(tagFirst.getName(), tagDto.getName());
	}

	@Test
	void getByInvalidId() {
		Assertions.assertThrows(ServiceException.class, () -> tagService.getById(-3L));
	}

	@Test
	void getByIncorrectId() {
		when(tagRepository.getById(anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(ServiceException.class, () -> tagService.getById(3L));
	}

	@Test
	void getByIdGetWithException() {
		when(tagRepository.getById(anyLong())).thenThrow(RuntimeException.class);
		Assertions.assertThrows(ServiceNotFoundException.class, () -> tagService.getById(3L));
	}

	@Test
	void saveWithIncorrectId() {
		tagFirstDto.setId(-1L);
		Assertions.assertThrows(Exception.class, () -> tagService.save(tagFirstDto));
	}

	@Test
	void saveWithIncorrectName() {
		tagFirstDto.setName(Stream.generate(() -> "a").limit(65).collect(Collectors.joining()));
		Assertions.assertThrows(Exception.class, () -> tagService.save(tagFirstDto));
	}

	@Test
	void saveWithException() {
		Assertions.assertThrows(Exception.class, () -> tagService.save(tagFirstDto));
	}

	@Test
	void removeIncorrectId() {
		tagFirst.setId(-1L);
		Assertions.assertThrows(Exception.class, () -> tagService.remove(tagFirst.getId()));
	}
}