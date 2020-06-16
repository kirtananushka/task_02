package com.epam.esm.service.dto;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ErrorMessage;
import com.epam.esm.service.ServiceException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ModelMapper {

	public static Optional<Certificate> convertToCertificate(CertificateDTO certificateDTO) {
		Certificate certificate = new Certificate();
		try {
			certificate.setId(certificateDTO.getId());
			certificate.setName(certificateDTO.getName());
			certificate.setDescription(certificateDTO.getDescription());
			certificate.setPrice(certificateDTO.getPrice());
			certificate.setCreationDate(certificateDTO.getCreationDate());
			certificate.setModificationDate(certificateDTO.getModificationDate());
			certificate.setDuration(certificateDTO.getDuration());
			List<Tag> tagList = new ArrayList<>();
			for (TagDTO tagDTO : certificateDTO.getTags()) {
				Tag tag = new Tag();
				tag.setId(tagDTO.getId());
				tag.setName(tagDTO.getName());
				tagList.add(tag);
			}
			certificate.setTags(tagList);
		} catch (Exception e) {
			throw new ServiceException(
							ErrorMessage.ERROR_CERTIFICATE_CONVERSION, e);
		}
		return Optional.of(certificate);
	}

	public static Optional<CertificateDTO> convertToCertificateDTO(Certificate certificate) {
		CertificateDTO certificateDTO = new CertificateDTO();
		try {
			certificateDTO.setId(certificate.getId());
			certificateDTO.setName(certificate.getName());
			certificateDTO.setDescription(certificate.getDescription());
			certificateDTO.setPrice(certificate.getPrice());
			certificateDTO.setCreationDate(certificate.getCreationDate());
			certificateDTO.setModificationDate(certificate.getModificationDate());
			certificateDTO.setDuration(certificate.getDuration());
			List<TagDTO> tagDTOList = new ArrayList<>();
			for (Tag tag : certificate.getTags()) {
				TagDTO tagDTO = new TagDTO();
				tagDTO.setId(tag.getId());
				tagDTO.setName(tag.getName());
				tagDTOList.add(tagDTO);
			}
			certificateDTO.setTags(tagDTOList);
		} catch (Exception e) {
			throw new ServiceException(
							ErrorMessage.ERROR_CERTIFICATE_CONVERSION, e);
		}
		return Optional.of(certificateDTO);
	}

	public static Optional<Tag> convertToTag(TagDTO tagDTO) {
		Tag tag = new Tag();
		try {
			tag.setId(tagDTO.getId());
			tag.setName(tagDTO.getName());
		} catch (Exception e) {
			throw new ServiceException(ErrorMessage.ERROR_TAG_CONVERSION, e);
		}
		return Optional.of(tag);
	}

	public static Optional<TagDTO> convertToTagDTO(Tag tag) {
		TagDTO tagDTO = new TagDTO();
		try {
			tagDTO.setId(tag.getId());
			tagDTO.setName(tag.getName());
		} catch (Exception e) {
			throw new ServiceException(ErrorMessage.ERROR_TAG_CONVERSION, e);
		}
		return Optional.of(tagDTO);
	}
}
