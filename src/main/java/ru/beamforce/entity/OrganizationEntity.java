package ru.beamforce.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Andrey Korneychuk on 18-Feb-22
 * @version 1.0
 */
@Entity
@Table(name = "organizations")
public class OrganizationEntity extends BaseEntity {

	@Pattern(regexp = "[a-zA-Z\\d_-]+", message = "Некорректный паттерн")
	@Size(min = 3, max = 25, message = "Длина имени должна быть от 3 до 25")
	private String name;
	private long adminId;
	private String joinToken;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
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