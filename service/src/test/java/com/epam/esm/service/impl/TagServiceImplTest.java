package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.TagDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

	private static Tag tagFirst;
	private static Tag tagSecond;
	private static TagDTO tagFirstDTO;
	private static TagDTO tagSecondDTO;
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
		tagFirstDTO = new TagDTO();
		tagFirstDTO.setId(1L);
		tagFirstDTO.setName("First tag");
		tagSecondDTO = new TagDTO();
		tagSecondDTO.setId(2L);
		tagSecondDTO.setName("Second tag");
	}

	@Test
	void getAll() {
		when(tagRepository.getAll()).thenReturn(tagList);
		Collection<TagDTO> tagDTOCollection = tagService.getAll();
		List<TagDTO> tagDTOList = new ArrayList<>(tagDTOCollection);
		Assertions.assertEquals(2, tagDTOList.size());
		Assertions.assertEquals(TagDTO.class, tagDTOList.get(0).getClass());
	}

	@Test
	void getById() {
		when(tagRepository.getById(anyLong())).thenReturn(Optional.of(tagFirst));
		Optional<TagDTO> optionalTagDTO = tagService.getById(tagFirst.getId());
		TagDTO tagDTO = optionalTagDTO.get();
		Assertions.assertEquals(tagFirst.getId(), tagDTO.getId());
		Assertions.assertEquals(tagFirst.getName(), tagDTO.getName());
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
		Assertions.assertThrows(ServiceException.class, () -> tagService.getById(3L));
	}

	@Test
	void save() {
		when(tagRepository.save(any())).thenReturn(Optional.of(tagSecond));
		Optional<TagDTO> optionalTagDTO = tagService.save(tagSecondDTO);
		TagDTO tagDTO = optionalTagDTO.get();
		Assertions.assertEquals(tagSecond.getId(), tagDTO.getId());
		Assertions.assertEquals(tagSecond.getName(), tagDTO.getName());
	}

	@Test
	void saveWithIncorrectId() {
		tagFirstDTO.setId(-1L);
		Assertions.assertThrows(ServiceException.class, () -> tagService.save(tagFirstDTO));
	}

	@Test
	void saveWithIncorrectName() {
		tagFirstDTO.setName("ToooooooooooooooooooooooooooooooooooooooooLoooooooooooooooooooooooong");
		Assertions.assertThrows(ServiceException.class, () -> tagService.save(tagFirstDTO));
	}

	@Test
	void saveWithException() {
		Assertions.assertThrows(ServiceException.class, () -> tagService.save(tagFirstDTO));
	}

	@Test
	void removeIncorrectId() {
		tagFirst.setId(-1L);
		Assertions.assertThrows(ServiceException.class, () -> tagService.remove(tagFirst.getId()));
	}
}