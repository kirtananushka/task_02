package com.epam.esm.service.config;

import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.impl.CertificateServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import com.epam.esm.service.validation.Validator;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceTestConfig {

	@Bean
	public CertificateRepository certificateRepository() {
		return Mockito.mock(CertificateRepository.class);
	}

	@Bean
	public TagRepository tagRepository() {
		return Mockito.mock(TagRepository.class);
	}

	@Bean
	public Validator validator() {
		return new Validator();
	}

	@Bean
	public CertificateServiceImpl certificateService() {
		return new CertificateServiceImpl(certificateRepository(), tagService(), validator());
	}

	@Bean
	public TagServiceImpl tagService() {
		return new TagServiceImpl(tagRepository(), validator());
	}
}


