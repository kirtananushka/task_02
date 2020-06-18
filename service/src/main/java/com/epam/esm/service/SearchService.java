package com.epam.esm.service;

import com.epam.esm.parameterwrapper.ParameterWrapper;
import com.epam.esm.service.dto.CertificateDTO;
import com.epam.esm.service.dto.TagDTO;

import java.util.Collection;

public interface SearchService {

	Collection<CertificateDTO> searchCertificate(ParameterWrapper parameterWrapper);

	Collection<TagDTO> searchTag(ParameterWrapper parameterWrapper);
}
