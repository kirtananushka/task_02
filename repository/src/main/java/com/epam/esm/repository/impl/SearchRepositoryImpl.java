package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.parameterwrapper.ParameterWrapper;
import com.epam.esm.repository.SearchRepository;
import com.epam.esm.repository.mapper.CertificateMapper;
import com.epam.esm.repository.mapper.TagMapper;
import com.epam.esm.repository.querybuilder.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;

@Repository
public class SearchRepositoryImpl extends NamedParameterJdbcDaoSupport
				implements SearchRepository {

	private TagRepositoryImpl tagRepository;
	private QueryBuilder queryBuilder;

	@Autowired
	public SearchRepositoryImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Autowired
	public void setTagRepository(TagRepositoryImpl tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Autowired
	public void setQueryBuilder(QueryBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

	@Override
	public Collection<Certificate> searchCertificate(ParameterWrapper params) {
		StringBuilder builder = queryBuilder.buildCertificateColumns(params)
		                                    .append(queryBuilder.buildSorting(params))
		                                    .append(queryBuilder.buildPagination(params));
		String query = queryBuilder.makeReplacement(builder);
		System.out.println(query);
		return searchCertificate(query);
	}

	@Override
	public Collection<Tag> searchTag(ParameterWrapper params) {
		StringBuilder builder = queryBuilder.buildTagColumns(params)
		                                    .append(queryBuilder.buildPagination(params));
		String query = queryBuilder.makeReplacement(builder);
		System.out.println(query);
		return searchTag(query);
	}

	private Collection<Certificate> searchCertificate(String query) {
		List<Certificate> certificateList = getNamedParameterJdbcTemplate()
						.query(query, new CertificateMapper());
		for (Certificate certificate : certificateList) {
			certificate.setTags(tagRepository.getTagsByCertificateId(certificate.getId()));
		}
		return certificateList;
	}

	private Collection<Tag> searchTag(String query) {
		List<Tag> tagList = getNamedParameterJdbcTemplate()
						.query(query, new TagMapper());
		return tagList;
	}
}