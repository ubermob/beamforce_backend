package ru.beamforce.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Andrey Korneychuk on 10-Apr-22
 * @version 1.0
 */
@Entity
@Table(name = "models")
@TypeDef(name = "json", typeClass = JsonType.class)
public class ModelEntity extends AbstractModelAttributeEntity {

	private String model;

	public ModelEntity() {
		super(LocalDateTime.now());
	}

	public ModelEntity(String model, String name, String commentary) {
		super(name, commentary);
		this.model = model;
		super.setLocalDateTime(LocalDateTime.now());
	}
}