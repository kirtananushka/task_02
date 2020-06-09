package com.epam.esm.entity;

import com.epam.esm.util.LocalDateDeserializer;
import com.epam.esm.util.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class Certificate implements Serializable {

	private static final long serialVersionUID = -8789550034634052884L;
	private long certId;
	private String certName;
	private String description;
	private BigDecimal price;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate creationDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate modificationDate;
	private int duration;

	public Certificate() {
	}

	public long getCertId() {
		return certId;
	}

	public void setCertId(long certId) {
		this.certId = certId;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDate modificationDate) {
		this.modificationDate = modificationDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Certificate that = (Certificate) o;
		if (certId != that.certId) {
			return false;
		}
		if (duration != that.duration) {
			return false;
		}
		if (certName != null ? !certName.equals(that.certName) : that.certName != null) {
			return false;
		}
		if (description != null ? !description.equals(that.description) : that.description != null) {
			return false;
		}
		if (price != null ? !price.equals(that.price) : that.price != null) {
			return false;
		}
		if (creationDate != null ? !creationDate.equals(that.creationDate)
		                         : that.creationDate != null) {
			return false;
		}
		return modificationDate != null ? modificationDate.equals(that.modificationDate)
		                                : that.modificationDate == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (certId ^ (certId >>> 32));
		result = 31 * result + (certName != null ? certName.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
		result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
		result = 31 * result + duration;
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Certificate{");
		sb.append("id=").append(certId);
		sb.append(", name='").append(certName).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", price=").append(price);
		sb.append(", creationDate=").append(creationDate);
		sb.append(", modificationDate=").append(modificationDate);
		sb.append(", duration=").append(duration);
		sb.append('}');
		return sb.toString();
	}
}
