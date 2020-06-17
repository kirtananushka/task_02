package com.epam.esm.service.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.CertificateDTO;
import com.epam.esm.service.dto.TagDTO;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CertificateServiceImplTest {

    private static Tag tag;
    private static TagDTO tagDTO;
    private static Certificate certificateFirst;
    private static Certificate certificateSecond;
    private static CertificateDTO certificateFirstDTO;
    private static CertificateDTO certificateSecondDTO;
    private static List<Tag> tagList;
    private static List<TagDTO> tagListDTO;

    private CertificateService certificateService;

    @Mock
    private CertificateRepository certificateRepository;

    @Mock
    private TagService tagService;

    static {
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

    @BeforeEach
    void setup() {

        certificateService = new CertificateServiceImpl(certificateRepository, tagService);
    }

    @Test
    void save() {
        when(certificateRepository.save(any())).thenReturn(Optional.of(certificateSecond));

        //or   doReturn(Optional.of(certificateSecond)).when(certificateRepository).save(any());

        Assertions.assertNotNull(certificateService.save(certificateSecondDTO).get());
    }


}