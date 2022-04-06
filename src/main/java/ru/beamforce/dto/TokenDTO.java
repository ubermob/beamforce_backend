package ru.beamforce.dto;

import ru.beamforce.shortobject.Token;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
public class TokenDTO extends Token {

	public TokenDTO(String token) {
		super(token);
	}

	public void setToken(String token) {
		this.token = token;
	}
}