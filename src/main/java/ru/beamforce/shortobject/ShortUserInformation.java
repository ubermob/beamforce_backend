package ru.beamforce.shortobject;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
public class ShortUserInformation {

	private String userName;
	private String userOrganization;

	public ShortUserInformation(String userName, String userOrganization) {
		this.userName = userName;
		this.userOrganization = userOrganization;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserOrganization() {
		return userOrganization;
	}
}