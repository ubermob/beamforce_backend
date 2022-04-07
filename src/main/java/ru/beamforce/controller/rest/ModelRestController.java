package ru.beamforce.controller.rest;

import modelutil.container.GridContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.beamforce.bean.RandomToken;
import ru.beamforce.tmp.GridService;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 01-Feb-22
 * @version 1.0
 */
@RestController
public class ModelRestController {

	@Autowired
	private RandomToken randomToken;
	@Autowired
	private GridService gridService;

	// TODO remove
/*	@RequestMapping("/api/token")
	public List<Token> token(@RequestParam(required = false) Integer number) {
		if (number == null) {
			number = 1;
		}
		List<Token> tokens = new ArrayList<>();
		for (int i = 0; i < number; i++) {
			tokens.add(new Token(randomToken.getToken(32)));
		}
		return tokens;
	}

	@RequestMapping("/api/tiny-token")
	public List<Token> tinyToken() {
		List<Token> tokens = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			tokens.add(new Token(randomToken.getTinyToken(32)));
		}
		return tokens;
	}*/

	// TODO remove
	@RequestMapping("/api/whoiam")
	public String[] whoIAm(Principal principal) {
		return new String[]{principal.getName(), "" + principal.hashCode()};
	}

	@RequestMapping("/api/grid")
	public GridContainer getGridById(@RequestParam() Integer id) {
		return gridService.get(id);
	}
}