package ru.beamforce.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Andrey Korneychuk on 09-Apr-22
 * @version 1.0
 */
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}