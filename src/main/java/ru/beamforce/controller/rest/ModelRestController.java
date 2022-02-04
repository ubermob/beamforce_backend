package ru.beamforce.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.beamforce.bean.RandomToken;
import ru.beamforce.shortobject.HelloMessage;
import ru.beamforce.shortobject.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Korneychuk on 01-Feb-22
 * @version 1.0
 */
@RestController
public class ModelRestController {

	@RequestMapping("/api/hello")
	public HelloMessage hello() {
		return new HelloMessage("hello from API");
	}

	@RequestMapping("/api/token")
	public List<Token> token() {
		RandomToken randomToken = new RandomToken();
		List<Token> tokens = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			tokens.add(new Token(randomToken.getToken(32)));
		}
		return tokens;
	}

	@RequestMapping("/api/tiny-token")
	public List<Token> tinyToken() {
		RandomToken randomToken = new RandomToken();
		List<Token> tokens = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			tokens.add(new Token(randomToken.getTinyToken(32)));
		}
		return tokens;
	}
}