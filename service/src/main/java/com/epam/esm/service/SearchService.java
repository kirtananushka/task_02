package com.epam.esm.service;

import com.epam.esm.parameterwrapper.ParameterWrapper;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.dto.TagDto;

import java.util.Collection;

public interface SearchService {

	Collection<CertificateDto> searchCertificate(ParameterWrapper parameterWrapper);

	Collection<TagDto> searchTag(ParameterWrapper parameterWrapper);
}
