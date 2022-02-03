package ru.beamforce.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Andrey Korneychuk on 02-Feb-22
 * @version 1.0
 */
public class RegistrationUser {

	@NotBlank(message = "blanked")
	@Size(min = 3, max = 25, message = "size")
	private String name;
	@Email(message = "email")
	private String email;
	@NotBlank(message = "blanked")
	@Size(min = 6, max = 50, message = "size")
	private String password;

	public RegistrationUser() {
	}

	public RegistrationUser(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegistrationUser{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}