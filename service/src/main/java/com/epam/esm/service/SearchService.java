package com.epam.esm.service;

import com.epam.esm.parameterwrapper.ParameterWrapper;
import com.epam.esm.service.dto.CertificateDTO;

import java.util.Collection;

public interface SearchService {

	Collection<CertificateDTO> search(ParameterWrapper parameterWrapper);
}
