package ru.beamforce.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Andrey Korneychuk on 27-Apr-22
 * @version 1.0
 */
@Entity
@Table(name = "feedback")
public class FeedbackEntity extends AbstractModelAttributeEntity {

	private String adminCommentary;

	public String getAdminCommentary() {
		return adminCommentary;
	}

	public void setAdminCommentary(String adminCommentary) {
		this.adminCommentary = adminCommentary;
	}
}