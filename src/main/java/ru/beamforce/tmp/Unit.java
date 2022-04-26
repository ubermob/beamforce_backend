package ru.beamforce.tmp;

import javax.persistence.*;

@Entity
@Table(name = "unit")
public class Unit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	@Column(length = 512)
	private String name;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
		item.setUnit(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}