package ru.beamforce.restentity;

/**
 * @author Andrey Korneychuk on 01-Feb-22
 * @version 1.0
 */
public class HelloMessage {

	private String message;

	public HelloMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}