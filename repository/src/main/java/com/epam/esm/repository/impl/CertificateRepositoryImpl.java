package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.mapper.CertificateMapper;
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
public class CertificateRepositoryImpl extends NamedParameterJdbcDaoSupport implements CertificateRepository {

    public static final String QUERY_GET_BY_ID =
            "SELECT id, name, description, price, creation_date, modification_date, duration\n"
                    + "FROM certificates WHERE id = :id;";
    public static final String QUERY_GET_ALL =
            "SELECT id, name, description, price, creation_date, modification_date, duration\n"
                    + " FROM certificates ORDER BY id;";
    public static final String QUERY_ADD =
            "INSERT INTO certificates(name, description, price, creation_date,\n"
                    + "modification_date, duration)\n"
                    + "VALUES (:name, :description, :price, :creation_date,\n"
                    + ":modification_date, :duration);";
    public static final String QUERY_UPDATE =
            "UPDATE certificates\n"
                    + "SET name = :name, description = :description, price = :price,\n"
                    + "modification_date = :modification_date,\n"
                    + "duration = :duration WHERE certificates.id = :id;";
    public static final String QUERY_REMOVE =
            "DELETE FROM certificates WHERE id = :id;";
    public static final String QUERY_UNBIND =
            "DELETE FROM certificate_tag WHERE certificate_id = :id;";

    private final TagRepository tagRepository;

    @Autowired
    public CertificateRepositoryImpl(DataSource dataSource, TagRepository tagRepository) {
        super.setDataSource(dataSource);
        this.tagRepository = tagRepository;
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
        Long certId = (Long) certKeyHolder.getKeys().get("id");
        certificate.setId(certId);
        if (Objects.nonNull(certificate.getTags())) {
            tagRepository.bindCertificateTag(certificate);
        }
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
                //.addValue("creation_date", certificate.getCreationDate())
                .addValue("modification_date", certificate.getModificationDate())
                .addValue("duration", certificate.getDuration());
        getNamedParameterJdbcTemplate().update(QUERY_UPDATE, params, keyHolder);
        Long certId = (Long) keyHolder.getKeys().get("id");
        certificate.setId(certId);
        unbindCertificateTag(certificate);
        tagRepository.bindCertificateTag(certificate);
        return getById(certId);
    }

    @Override
    public List<Certificate> getAll() {
        List<Certificate> certificateList = getNamedParameterJdbcTemplate()
                .query(QUERY_GET_ALL, new CertificateMapper());
        for (Certificate certificate : certificateList) {
            certificate.setTags(tagRepository.getTagsByCertificateId(certificate.getId()));
        }
        return certificateList;
    }

    @Override
    public Optional<Certificate> getById(Long id) {
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        Certificate certificate = getNamedParameterJdbcTemplate()
                .query(QUERY_GET_BY_ID, params, new CertificateMapper())
                .stream()
                .findAny()
                .orElse(null);
        certificate.setTags(tagRepository.getTagsByCertificateId(certificate.getId()));
        return Optional.of(certificate);
    }

    private void unbindCertificateTag(Certificate certificate) {
        SqlParameterSource params = new MapSqlParameterSource("id", certificate.getId());
        getNamedParameterJdbcTemplate().update(QUERY_UNBIND, params);
    }
}
