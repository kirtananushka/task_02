package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.mapper.CertificateMapper;
import com.epam.esm.repository.mapper.TagMapper;
import com.epam.esm.specification.CertificateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class CertificateRepositoryImpl extends NamedParameterJdbcDaoSupport
				implements CertificateRepository {

	public static final String QUERY_GET_BY_ID =
					"SELECT cert_id, cert_name, description, price, creation_date, modification_date,\n"
									+ "duration, tag_id, tag_name FROM certificates LEFT JOIN (tags INNER JOIN\n"
									+ "linkage ON tag_id = tag_id_fk) ON cert_id = cert_id_fk WHERE cert_id = :id";
	public static final String QUERY_GET_ALL =
					"SELECT cert_id, cert_name, description, price, creation_date, modification_date,\n"
									+ "duration, tag_id, tag_name FROM certificates LEFT JOIN (tags INNER JOIN\n"
									+ "linkage ON tag_id = tag_id_fk) ON cert_id = cert_id_fk ORDER BY cert_id";
	public static final String QUERY_ADD = "INSERT INTO certificates(\n"
					+ "cert_name, description, price, creation_date, modification_date, duration)\n"
					+ "VALUES (:name, :description, :price, :creation_date, :modification_date,\n"
					+ ":duration);";
	public static final String QUERY_UPDATE = "UPDATE certificates\n"
					+ "SET cert_name = :name, description = :description, price = :price,\n"
					+ "creation_date = :creation_date, modification_date = :modification_date,\n"
					+ "duration = :duration WHERE cert_id = :id;";
	public static final String QUERY_REMOVE = "DELETE FROM certificates\n"
					+ "WHERE cert_id = :id;";
	public static final String QUERY_GET_TAGS_BY_CERT =
					"SELECT tag_id, tag_name FROM tags INNER JOIN linkage ON tag_id = tag_id_fk"
									+ " WHERE cert_id_fk = :id;";
	public static final String QUERY_ADD_TAGS = "INSERT INTO tags (tag_name) VALUES (:tag_name);";
	public static final String QUERY_ADD_LINKAGE = "INSERT INTO linkage (cert_id_fk, tag_id_fk)\n"
					+ "VALUES (:cert_id, :tag_id);";
	public static final String QUERY_REMOVE_LINKAGE = "DELETE FROM linkage\n"
					+ "WHERE cert_id_fk = :id;";
	public static final String QUERY_GET_TAG_BY_NAME = "SELECT tag_id FROM tags\n"
					+ "WHERE tag_name = :name";

	@Autowired
	public CertificateRepositoryImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public Optional<Certificate> save(Certificate certificate) {
		KeyHolder certKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource params = new MapSqlParameterSource(
						"name", certificate.getName())
						.addValue("description", certificate.getDescription())
						.addValue("price", certificate.getPrice())
						.addValue("creation_date", certificate.getCreationDate())
						.addValue("modification_date", certificate.getModificationDate())
						.addValue("duration", certificate.getDuration());
		getNamedParameterJdbcTemplate().update(QUERY_ADD, params, certKeyHolder);
		long certId = (long) certKeyHolder.getKeys().get("cert_id");
		certificate.setId(certId);
		saveTags(certificate);
		return getById(certId);
	}

	@Override
	public void remove(Certificate certificate) {
		SqlParameterSource params = new MapSqlParameterSource("id", certificate.getId());
		getNamedParameterJdbcTemplate().update(QUERY_REMOVE, params);
	}

	@Override
	public Optional<Certificate> update(Certificate certificate) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource params = new MapSqlParameterSource("id", certificate.getId())
						.addValue("name", certificate.getName())
						.addValue("description", certificate.getDescription())
						.addValue("price", certificate.getPrice())
						.addValue("creation_date", certificate.getCreationDate())
						.addValue("modification_date", certificate.getModificationDate())
						.addValue("duration", certificate.getDuration());
		getNamedParameterJdbcTemplate().update(QUERY_UPDATE, params, keyHolder);
		long certId = (long) keyHolder.getKeys().get("cert_id");
		certificate.setId(certId);
		removeLinkage(certificate);
		saveTags(certificate);
		return getById(certId);
	}

	@Override
	public List<Certificate> query(CertificateSpecification specification) {
		return null;
	}

	@Override
	public List<Certificate> getAll() {
		List<Certificate> certificateList = getNamedParameterJdbcTemplate()
						.query(QUERY_GET_ALL, new CertificateMapper());
		for (Certificate certificate : certificateList) {
			certificate.setTags(getTagsByCertificateId(certificate.getId()));
		}
		return certificateList;
	}

	@Override
	public Optional<Certificate> getById(long id) {
		SqlParameterSource params = new MapSqlParameterSource("id", id);
		Certificate certificate = getNamedParameterJdbcTemplate()
						.query(QUERY_GET_BY_ID, params, new CertificateMapper())
						.stream()
						.findAny()
						.orElse(null);
		certificate.setTags(getTagsByCertificateId(certificate.getId()));
		return Optional.of(certificate);
	}

	private List<Tag> getTagsByCertificateId(long id) {
		SqlParameterSource params = new MapSqlParameterSource("id", id);
		return getNamedParameterJdbcTemplate().query(QUERY_GET_TAGS_BY_CERT, params, new TagMapper());
	}

	private long getTagByName(Tag tag) {
		SqlParameterSource params = new MapSqlParameterSource("name", tag.getName());
		return getNamedParameterJdbcTemplate()
						.query(QUERY_GET_TAG_BY_NAME, params, (resultSet, i) -> resultSet.getLong(1))
						.stream()
						.findAny()
						.orElse(0L);
	}

	private void saveTags(Certificate certificate) {
		List<Tag> tagList = certificate.getTags();
		KeyHolder tagKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource tagParams;
		SqlParameterSource linkageParams;
		for (Tag tag : tagList) {
			long tagId = getTagByName(tag);
			if (tagId == 0) {
				tagParams = new MapSqlParameterSource("tag_name", tag.getName());
				getNamedParameterJdbcTemplate().update(QUERY_ADD_TAGS, tagParams, tagKeyHolder);
				tagId = (long) tagKeyHolder.getKeys().get("tag_id");
			}
			linkageParams = new MapSqlParameterSource("cert_id", certificate.getId())
							.addValue("tag_id", tagId);
			getNamedParameterJdbcTemplate().update(QUERY_ADD_LINKAGE, linkageParams);
		}
	}

	private void removeLinkage(Certificate certificate) {
		SqlParameterSource params = new MapSqlParameterSource("id", certificate.getId());
		getNamedParameterJdbcTemplate().update(QUERY_REMOVE_LINKAGE, params);
	}
}
