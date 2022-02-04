package ru.beamforce.shortobject;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Andrey Korneychuk on 02-Feb-22
 * @version 1.0
 */
public class RegistrationUser {

	@Pattern(regexp = "[a-zA-Z\\d_-]+", message = "Некорректный паттерн")
	@Size(min = 3, max = 25, message = "Длина имени должна быть от 3 до 25")
	private String name;
	@Email(message = "Некорректный email")
	private String email;
	@Pattern(regexp = "[a-zA-Z\\d_-]+", message = "Некорректный паттерн")
	@Size(min = 6, max = 50, message = "Длина пароля должна быть от 6 до 50")
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

	public void trimEmail() {
		if (email != null && email.length() == 0) {
			email = null;
		}
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