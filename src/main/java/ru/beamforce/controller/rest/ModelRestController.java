package ru.beamforce.controller.rest;

import modelutil.container.GridContainer;
import modelutil.test.Sample;
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

	@RequestMapping("/api/whoiam")
	public String[] whoIAm(Principal principal) {
		return new String[]{principal.getName(), "" + principal.hashCode()};
	}

	@RequestMapping("/api/grid")
	public GridContainer getGridById(@RequestParam() Integer id) {
		return gridService.get(id);
	}

	@RequestMapping("/free-api/hello")
	public GridContainer getGridExample() {
		return Sample.getSample();
	}

	@RequestMapping("/free-api/hello")
	public Hello freeHello() {
		return new Hello("Value 1", "Value 2");
	}

	private class Hello {

		private String field1;
		private String field2;

		public Hello(String field1, String field2) {
			this.field1 = field1;
			this.field2 = field2;
		}

		public String getField1() {
			return field1;
		}

		public String getField2() {
			return field2;
		}
	}
}