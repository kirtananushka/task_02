package com.epam.esm.service.dto;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ErrorMessage;
import com.epam.esm.service.ServiceException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ModelMapper {

	public static Optional<Certificate> convertToCertificate(CertificateDto certificateDto) {
		Certificate certificate = new Certificate();
		try {
			certificate.setId(certificateDto.getId());
			certificate.setName(certificateDto.getName());
			certificate.setDescription(certificateDto.getDescription());
			certificate.setPrice(certificateDto.getPrice());
			certificate.setCreationDate(certificateDto.getCreationDate());
			certificate.setModificationDate(certificateDto.getModificationDate());
			certificate.setDuration(certificateDto.getDuration());
			List<Tag> tagList = new ArrayList<>();
			if (Objects.nonNull(certificateDto.getTags())) {
				for (TagDto tagDto : certificateDto.getTags()) {
					Tag tag = new Tag();
					tag.setId(tagDto.getId());
					tag.setName(tagDto.getName());
					tagList.add(tag);
				}
			}
			certificate.setTags(tagList);
		} catch (Exception e) {
			throw new ServiceException(ErrorMessage.ERROR_CERTIFICATE_CONVERSION, e);
		}
		return Optional.of(certificate);
	}

	public static Optional<CertificateDto> convertToCertificateDto(Certificate certificate) {
		CertificateDto certificateDto = new CertificateDto();
		try {
			if (Objects.nonNull(certificate.getId())) {
				certificateDto.setId(certificate.getId());
			}
			certificateDto.setName(certificate.getName());
			certificateDto.setDescription(certificate.getDescription());
			certificateDto.setPrice(certificate.getPrice());
			certificateDto.setCreationDate(certificate.getCreationDate());
			certificateDto.setModificationDate(certificate.getModificationDate());
			certificateDto.setDuration(certificate.getDuration());
			List<TagDto> tagDtoList = new ArrayList<>();
			for (Tag tag : certificate.getTags()) {
				TagDto tagDto = new TagDto();
				if (Objects.nonNull(tag.getId())) {
					tagDto.setId(tag.getId());
				}
				tagDto.setName(tag.getName());
				tagDtoList.add(tagDto);
			}
			certificateDto.setTags(tagDtoList);
		} catch (Exception e) {
			throw new ServiceException(ErrorMessage.ERROR_CERTIFICATE_CONVERSION, e);
		}
		return Optional.of(certificateDto);
	}

	public static Optional<Tag> convertToTag(TagDto tagDto) {
		Tag tag = new Tag();
		try {
			if (Objects.nonNull(tagDto.getId())) {
				tag.setId(tagDto.getId());
			}
			tag.setName(tagDto.getName());
		} catch (Exception e) {
			throw new ServiceException(ErrorMessage.ERROR_TAG_CONVERSION, e);
		}
		return Optional.of(tag);
	}

	public static Optional<TagDto> convertToTagDto(Tag tag) {
		TagDto tagDto = new TagDto();
		try {
			tagDto.setId(tag.getId());
			tagDto.setName(tag.getName());
		} catch (Exception e) {
			throw new ServiceException(ErrorMessage.ERROR_TAG_CONVERSION, e);
		}
		return Optional.of(tagDto);
	}
}