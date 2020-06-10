package com.epam.esm.entity;

import java.io.Serializable;

public class Tag implements Serializable {

	private static final long serialVersionUID = 4793809016058885307L;
	private long id;
	private String name;

	public Tag() {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Tag tag = (Tag) o;
		if (id != tag.id) {
			return false;
		}
		return name != null ? name.equals(tag.name) : tag.name == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Tag{");
		sb.append("id=").append(id);
		sb.append(", description='").append(name).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
