package ru.beamforce.dto;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
public class GridInputDTO {

	private String along;
	private String across;

	public GridInputDTO() {
	}

	public GridInputDTO(String along, String across) {
		this.along = along;
		this.across = across;
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
}