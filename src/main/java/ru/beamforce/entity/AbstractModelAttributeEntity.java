package ru.beamforce.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author Andrey Korneychuk on 10-Apr-22
 * @version 1.0
 */
@MappedSuperclass
public abstract class AbstractModelAttributeEntity extends BaseEntity {

	private long authorId;
	private String name;
	private String commentary;
	@Column(name = "created_time")
	private LocalDateTime localDateTime;

	public AbstractModelAttributeEntity() {
		localDateTime = LocalDateTime.now();
	}

	public AbstractModelAttributeEntity(long authorId, String name, String commentary) {
		this.authorId = authorId;
		this.name = name;
		this.commentary = commentary;
		localDateTime = LocalDateTime.now();
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
}