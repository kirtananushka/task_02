package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;
import com.epam.esm.parameterwrapper.ParameterWrapper;

import java.util.Collection;

public interface SearchRepository {

	Collection<Certificate> search(ParameterWrapper parameterWrapper);
}
