package com.epam.esm.entity;

import com.epam.esm.util.LocalDateDeserializer;
import com.epam.esm.util.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Certificate implements Serializable {

	private static final long serialVersionUID = -8789550034634052884L;
	private long id;
	private String name;
	private String description;
	private BigDecimal price;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate creationDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate modificationDate;
	private int duration;
	private List<Tag> tags;

	public Certificate() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
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
		if (id != that.id) {
			return false;
		}
		if (duration != that.duration) {
			return false;
		}
		if (name != null ? !name.equals(that.name) : that.name != null) {
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
		if (modificationDate != null ? !modificationDate.equals(that.modificationDate)
		                             : that.modificationDate != null) {
			return false;
		}
		return tags != null ? tags.equals(that.tags) : that.tags == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
		result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
		result = 31 * result + duration;
		result = 31 * result + (tags != null ? tags.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Certificate{");
		sb.append("certId=").append(id);
		sb.append(", certName='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", price=").append(price);
		sb.append(", creationDate=").append(creationDate);
		sb.append(", modificationDate=").append(modificationDate);
		sb.append(", duration=").append(duration);
		sb.append(", tags=").append(tags);
		sb.append('}');
		return sb.toString();
	}
}
