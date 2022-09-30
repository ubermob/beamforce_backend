package ru.beamforce.dto;

/**
 * @author Andrey Korneychuk on 21-Apr-22
 * @version 1.0
 */
public class ModelInputDTO {

	private String name;
	private String commentary;
	private Long gridId;
	private Byte accessLevel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public Long getGridId() {
		return gridId;
	}

	public void setGridId(Long gridId) {
		this.gridId = gridId;
	}

	public Byte getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(Byte accessLevel) {
		this.accessLevel = accessLevel;
	}
}