package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.SearchRepository;
import com.epam.esm.repository.mapper.CertificateMapper;
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

	@Autowired
	public SearchRepositoryImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Autowired
	public void setTagRepository(TagRepositoryImpl tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Override
	public Collection<Certificate> filter(String name,
	                                      String price,
	                                      String creationDate,
	                                      String modificationDate,
	                                      String duration) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT"
						+ " id, name, description, price, creation_date, modification_date, duration"
						+ " FROM certificates ");
		if (!name.isEmpty()) {
			queryBuilder.append("WHERE name LIKE '%" + name + "%';");
		}
		if (!price.isEmpty()) {
			queryBuilder.append("WHERE price=" + price + ";");
		}
		if (!creationDate.isEmpty()) {
			queryBuilder.append("WHERE creation_date='" + creationDate + "';");
		}
		if (!modificationDate.isEmpty()) {
			queryBuilder.append("WHERE modification_date='" + modificationDate + "';");
		}
		if (!duration.isEmpty()) {
			queryBuilder.append("WHERE duration=" + duration + ";");
		}
		String query = queryBuilder.toString();
		query = query.replaceAll(";WHERE ", " AND ");
		query = query.replaceAll("=>", ">");
		query = query.replaceAll("=<", "<");
		query = query.replaceAll("=<>", "<>");
		query = query.replaceAll("=!", "!");
		return search(query);
	}

	private Collection<Certificate> search(String query) {
		List<Certificate> certificateList = getNamedParameterJdbcTemplate()
						.query(query, new CertificateMapper());
		for (Certificate certificate : certificateList) {
			certificate.setTags(tagRepository.getTagsByCertificateId(certificate.getId()));
		}
		return certificateList;
	}
}
