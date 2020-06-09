package com.epam.esm.repository.mapper;

import com.epam.esm.entity.Certificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CertificateMapper implements RowMapper<Certificate> {

	@Override
	public Certificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Certificate certificate = new Certificate();
		certificate.setCertId(resultSet.getLong("cert_id"));
		certificate.setCertName(resultSet.getString("cert_name"));
		certificate.setDescription(resultSet.getString("description"));
		certificate.setPrice(resultSet.getBigDecimal("price"));
		certificate.setCreationDate(
						resultSet.getTimestamp("creation_date").toLocalDateTime().toLocalDate());
		if (resultSet.getTimestamp("modification_date") != null) {
			certificate.setModificationDate(
							resultSet.getTimestamp("modification_date").toLocalDateTime().toLocalDate());
		}
		certificate.setDuration(resultSet.getInt("duration"));
		return certificate;
	}
}

