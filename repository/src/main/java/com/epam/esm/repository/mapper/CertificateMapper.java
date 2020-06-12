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
		certificate.setId(resultSet.getLong("id"));
		certificate.setName(resultSet.getString("name"));
		certificate.setDescription(resultSet.getString("description"));
		certificate.setPrice(resultSet.getBigDecimal("price"));
		certificate.setCreationDate(
						resultSet.getDate("creation_date").toLocalDate());
		if (resultSet.getDate("modification_date") != null) {
			certificate.setModificationDate(
							resultSet.getDate("modification_date").toLocalDate());
		}
		certificate.setDuration(resultSet.getInt("duration"));
		return certificate;
	}
}
  
