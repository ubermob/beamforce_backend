package ru.beamforce.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import modelutil.container.GridContainer;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/*
https://vladmihalcea.com/java-map-json-jpa-hibernate/
https://stackoverflow.com/questions/62232055/how-to-save-json-object-in-postgresql-using-hibernate-in-java
 */

/**
 * @author Andrey Korneychuk on 08-Apr-22
 * @version 1.0
 */
@Entity
@Table(name = "grids")
@TypeDef(name = "json", typeClass = JsonType.class)
public class GridEntity extends BaseEntity {

	@Type(type = "json")
	@Column(name = "grid_container")
	private GridContainer gridContainer;
	private String name;
	private String commentary;
	private LocalDateTime localDateTime;

	public GridEntity() {
		localDateTime = LocalDateTime.now();
	}

	public GridEntity(GridContainer gridContainer, String name, String commentary) {
		this.gridContainer = gridContainer;
		this.name = name;
		this.commentary = commentary;
		localDateTime = LocalDateTime.now();
	}

	public GridContainer getGridContainer() {
		return gridContainer;
	}

	public void setGridContainer(GridContainer gridContainer) {
		this.gridContainer = gridContainer;
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