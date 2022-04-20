package ru.beamforce.tmp;

import javax.persistence.*;

/**
 * @author Andrey Korneychuk on 19-Apr-22
 * @version 1.0
 */
@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;
	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}