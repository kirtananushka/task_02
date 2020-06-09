package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.mapper.CertificateMapper;
import com.epam.esm.specification.CertificateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CertificateRepositoryImpl implements CertificateRepository {

	public static final String QUERY_GET_BY_ID =
					"SELECT cert_id, cert_name, description, price, creation_date, modification_date, "
									+ "duration FROM certificates WHERE cert_id = :id";
	public static final String QUERY_GET_ALL =
					"SELECT cert_id, cert_name, description, price, creation_date, modification_date, "
									+ "duration FROM certificates";
	public static final String QUERY_ADD = "INSERT INTO certificates(\n"
					+ "cert_name, description, price, creation_date, modification_date, duration)\n"
					+ "VALUES (:name, :description, :price, :creation_date, :modification_date, "
					+ ":duration);";
	private Certificate certificate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public CertificateRepositoryImpl() {
	}

	@Autowired
	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	@Autowired
	public void setNamedParameterJdbcTemplate(
					NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Optional<Certificate> addCertificate(Certificate certificate) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource params = new MapSqlParameterSource(
						"name", certificate.getCertName())
						.addValue("description", certificate.getDescription())
						.addValue("price", certificate.getPrice())
						.addValue("creation_date", certificate.getCreationDate())
						.addValue("modification_date", certificate.getModificationDate())
						.addValue("duration", certificate.getDuration());
		namedParameterJdbcTemplate.update(QUERY_ADD, params, keyHolder);
		return getCertificate((long) keyHolder.getKeys().get("cert_id"));
	}

	@Override
	public void removeCertificate(Certificate certificate) {
	}

	@Override
	public void updateCertificate(Certificate certificate) {
	}

	@Override
	public List<Certificate> query(CertificateSpecification specification) {
		return null;
	}

	@Override
	public List<Certificate> getCertificates() {
		return namedParameterJdbcTemplate
						.query(QUERY_GET_ALL, new CertificateMapper());
	}

	@Override
	public Optional<Certificate> getCertificate(long id) {
		SqlParameterSource params = new MapSqlParameterSource("id", id);
		certificate = namedParameterJdbcTemplate
						.query(QUERY_GET_BY_ID, params, new CertificateMapper())
						.stream()
						.findAny()
						.orElse(null);
		return Optional.of(certificate);
	}
}
