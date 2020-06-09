package com.epam.esm.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Tag implements Serializable {

	private static final long serialVersionUID = 4793809016058885307L;
	private long tagId;
	private String tagName;

	public Tag() {
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
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
		if (tagId != tag.tagId) {
			return false;
		}
		return tagName != null ? tagName.equals(tag.tagName) : tag.tagName == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (tagId ^ (tagId >>> 32));
		result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Tag{");
		sb.append("id=").append(tagId);
		sb.append(", description='").append(tagName).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
