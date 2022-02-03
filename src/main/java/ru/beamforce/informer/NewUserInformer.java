package ru.beamforce.informer;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
public class NewUserInformer {

	private boolean isAvailableName;
	private boolean isAvailableEmail;

	public boolean isAvailableName() {
		return isAvailableName;
	}

	public void setAvailableName(boolean availableName) {
		isAvailableName = availableName;
	}

	public boolean isAvailableEmail() {
		return isAvailableEmail;
	}

	public void setAvailableEmail(boolean availableEmail) {
		isAvailableEmail = availableEmail;
	}

	public boolean hasErrors() {
		return !isAvailableName || !isAvailableEmail;
	}

	@Override
	public String toString() {
		return "NewUserInformer{" +
				"isAvailableName=" + isAvailableName +
				", isAvailableEmail=" + isAvailableEmail +
				'}';
	}
}