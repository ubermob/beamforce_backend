package ru.beamforce.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.beamforce.modelutil.container.GridContainer;

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
public class GridEntity extends AbstractModelAttributeEntity {

	@Type(type = "json")
	@Column(name = "grid_container")
	private GridContainer gridContainer;

	public GridEntity() {
	}

	public GridEntity(GridContainer gridContainer, long authorId, String name, String commentary) {
		super(authorId, name, commentary);
		this.gridContainer = gridContainer;
		super.setLocalDateTime(LocalDateTime.now());
	}

	public GridContainer getGridContainer() {
		return gridContainer;
	}

	public void setGridContainer(GridContainer gridContainer) {
		this.gridContainer = gridContainer;
	}
}