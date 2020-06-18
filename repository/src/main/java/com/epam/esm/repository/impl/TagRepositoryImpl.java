package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TagRepositoryImpl extends NamedParameterJdbcDaoSupport
				implements TagRepository {

	public static final String TAG_NAME = "tag_name";
	public static final String TAG_ID = "tag_id";
	public static final String CERT_ID = "cert_id";
	public static final String COLUMN_ID = "id";
	public static final String QUERY_GET_BY_ID =
					"SELECT id, name FROM tags WHERE id = :tag_id";
	public static final String QUERY_ADD =
					"INSERT INTO tags (name) VALUES (:tag_name);";
	public static final String QUERY_REMOVE =
					"DELETE FROM tags WHERE id = :tag_id;";
	public static final String QUERY_GET_TAGS_BY_CERT =
					"SELECT tags.id, tags.name FROM tags INNER JOIN certificate_tag ON tags.id = tag_id"
									+ " WHERE certificate_id = :cert_id;";
	public static final String QUERY_ADD_TAGS =
					"INSERT INTO tags (name) VALUES (:tag_name);";
	public static final String QUERY_ADD_LINKAGE =
					"INSERT INTO certificate_tag (certificate_id, tag_id) VALUES (:cert_id, :tag_id);";
	public static final String QUERY_GET_NAME_BY_ID =
					"SELECT name FROM tags WHERE id = :tag_id";

	@Autowired
	public TagRepositoryImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public Optional<Tag> save(Tag tag) {
		KeyHolder tagKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource params = new MapSqlParameterSource(TAG_NAME, tag.getName());
		getNamedParameterJdbcTemplate().update(QUERY_ADD, params, tagKeyHolder);
		return getById((Long) tagKeyHolder.getKeys().get(COLUMN_ID));
	}

	@Override
	public void remove(Tag tag) {
		SqlParameterSource params = new MapSqlParameterSource(TAG_ID, tag.getId());
		getNamedParameterJdbcTemplate().update(QUERY_REMOVE, params);
	}

	@Override
	public Optional<Tag> getById(Long id) {
		SqlParameterSource params = new MapSqlParameterSource(TAG_ID, id);
		Tag tag = getNamedParameterJdbcTemplate()
						.query(QUERY_GET_BY_ID, params, new TagMapper())
						.stream()
						.findAny()
						.orElse(null);
		return Optional.of(tag);
	}

	@Override
	public List<Tag> getTagsByCertificateId(Long id) {
		SqlParameterSource params = new MapSqlParameterSource(CERT_ID, id);
		return getNamedParameterJdbcTemplate().query(QUERY_GET_TAGS_BY_CERT, params, new TagMapper());
	}

	@Override
	public void bindCertificateTag(Certificate certificate) {
		List<Tag> tagList = certificate.getTags();
		KeyHolder tagKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource tagParams;
		SqlParameterSource linkageParams;
		for (Tag tag : tagList) {
			Long tagId = tag.getId();
			String tagName = tag.getName();
			if (Objects.isNull(tagId) && Objects.isNull(tagName)) {
				continue;
			}
			if (Objects.isNull(tagId) || Objects.isNull(getTagNameById(tag))) {
				if (Objects.isNull(tagName)) {
					tagName = "New tag instead of the missing tag with id " + tagId;
				}
				tagParams = new MapSqlParameterSource(TAG_NAME, tagName);
				getNamedParameterJdbcTemplate().update(QUERY_ADD_TAGS, tagParams, tagKeyHolder);
				tagId = (Long) tagKeyHolder.getKeys().get(COLUMN_ID);
			}
			linkageParams = new MapSqlParameterSource(CERT_ID, certificate.getId())
							.addValue(TAG_ID, tagId);
			getNamedParameterJdbcTemplate().update(QUERY_ADD_LINKAGE, linkageParams);
		}
	}

	private String getTagNameById(Tag tag) {
		SqlParameterSource params = new MapSqlParameterSource(TAG_ID, tag.getId());
		return getNamedParameterJdbcTemplate()
						.query(QUERY_GET_NAME_BY_ID, params, (resultSet, i) -> resultSet.getString(1))
						.stream()
						.findAny()
						.orElse(null);
	}
}
