package ru.beamforce.dto;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
public class GridInputDTO {

	private String along;
	private String across;
	private String name;
	private String commentary;

	public GridInputDTO() {
	}

	public GridInputDTO(String along, String across) {
		this.along = along;
		this.across = across;
	}

	public GridInputDTO(String along, String across, String name, String commentary) {
		this.along = along;
		this.across = across;
		this.name = name;
		this.commentary = commentary;
	}

	public String getAlong() {
		return along;
	}

	public void setAlong(String along) {
		this.along = along;
	}

	public String getAcross() {
		return across;
	}

	public void setAcross(String across) {
		this.across = across;
	}

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
}