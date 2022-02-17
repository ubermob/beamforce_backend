package ru.beamforce.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Andrey Korneychuk on 18-Feb-22
 * @version 1.0
 */
public class UpdatePasswordDTO {

	private String oldPassword;
	@Pattern(regexp = "[a-zA-Z\\d_-]+", message = "Некорректный паттерн")
	@Size(min = 6, max = 50, message = "Длина пароля должна быть от 6 до 50")
	private String newPassword;

	public UpdatePasswordDTO() {
	}

	public UpdatePasswordDTO(String oldPassword, String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}