package com.epam.esm.service;

import com.epam.esm.entity.Certificate;
import com.epam.esm.parameterwrapper.ParameterWrapper;

import java.util.Collection;

public interface SearchService {

	Collection<Certificate> search(ParameterWrapper parameterWrapper);
}
