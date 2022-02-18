package ru.beamforce.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Andrey Korneychuk on 18-Feb-22
 * @version 1.0
 */
@Entity
@Table(name = "organizations")
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Pattern(regexp = "[a-zA-Z\\d_-]+", message = "Некорректный паттерн")
	@Size(min = 3, max = 25, message = "Длина имени должна быть от 3 до 25")
	private String name;
	private Long adminId;
	private String joinToken;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getJoinToken() {
		return joinToken;
	}

	public void setJoinToken(String joinToken) {
		this.joinToken = joinToken;
	}

	@Override
	public String toString() {
		return "Organization{" +
				"id=" + id +
				", name='" + name + '\'' +
				", adminId=" + adminId +
				'}';
	}
}