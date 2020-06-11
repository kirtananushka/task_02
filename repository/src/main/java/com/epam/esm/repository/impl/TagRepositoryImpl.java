package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.mapper.TagMapper;
import com.epam.esm.specification.TagSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepositoryImpl extends NamedParameterJdbcDaoSupport
				implements TagRepository {

	public static final String QUERY_GET_BY_ID =
					"SELECT tag_id, tag_name FROM tags WHERE tag_id = :id";
	public static final String QUERY_GET_ALL =
					"SELECT tag_id, tag_name FROM tags ORDER BY tag_id";
	public static final String QUERY_ADD = "INSERT INTO tags(tag_name) VALUES (:name);";
	public static final String QUERY_REMOVE = "DELETE FROM tags\n"
					+ "WHERE tag_id = :id;";
	public static final String QUERY_GET_TAGS_BY_CERT =
					"SELECT tag_id, tag_name FROM tags INNER JOIN linkage ON tag_id = tag_id_fk"
									+ " WHERE cert_id_fk = :id;";
	public static final String QUERY_ADD_TAGS = "INSERT INTO tags (tag_name) VALUES (:tag_name);";
	public static final String QUERY_ADD_LINKAGE = "INSERT INTO linkage (cert_id_fk, tag_id_fk)\n"
					+ "VALUES (:cert_id, :tag_id);";
	public static final String QUERY_GET_TAG_BY_NAME = "SELECT tag_id FROM tags\n"
					+ "WHERE tag_name = :name";

	@Autowired
	public TagRepositoryImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public Optional<Tag> save(Tag tag) {
		KeyHolder tagKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource params = new MapSqlParameterSource("name", tag.getName());
		getNamedParameterJdbcTemplate().update(QUERY_ADD, params, tagKeyHolder);
		return getById((long) tagKeyHolder.getKeys().get("tag_id"));
	}

	@Override
	public void remove(Tag tag) {
		SqlParameterSource params = new MapSqlParameterSource("id", tag.getId());
		getNamedParameterJdbcTemplate().update(QUERY_REMOVE, params);
	}

	@Override
	public Collection<Tag> query(TagSpecification specification) {
		return null;
	}

	@Override
	public Collection<Tag> getAll() {
		return getNamedParameterJdbcTemplate()
						.query(QUERY_GET_ALL, new TagMapper());
	}

	@Override
	public Optional<Tag> getById(long id) {
		SqlParameterSource params = new MapSqlParameterSource("id", id);
		Tag tag = getNamedParameterJdbcTemplate()
						.query(QUERY_GET_BY_ID, params, new TagMapper())
						.stream()
						.findAny()
						.orElse(null);
		return Optional.of(tag);
	}

	public List<Tag> getTagsByCertificateId(long id) {
		SqlParameterSource params = new MapSqlParameterSource("id", id);
		return getNamedParameterJdbcTemplate().query(QUERY_GET_TAGS_BY_CERT, params, new TagMapper());
	}

	public long getTagByName(Tag tag) {
		SqlParameterSource params = new MapSqlParameterSource("name", tag.getName());
		return getNamedParameterJdbcTemplate()
						.query(QUERY_GET_TAG_BY_NAME, params, (resultSet, i) -> resultSet.getLong(1))
						.stream()
						.findAny()
						.orElse(0L);
	}

	public void saveTagsByCertificate(Certificate certificate) {
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
}
