package ru.beamforce.dto;

import javax.validation.constraints.Email;

/**
 * @author Andrey Korneychuk on 02-Feb-22
 * @version 1.0
 */
public class EmailDTO {

	@Email(message = "Некорректный email")
	private String email;

	public EmailDTO() {
	}

	public EmailDTO(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void trimEmail() {
		if (email != null && email.length() == 0) {
			email = null;
		}
	}
}