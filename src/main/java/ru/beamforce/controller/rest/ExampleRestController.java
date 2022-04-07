package ru.beamforce.controller.rest;

import modelutil.container.GridContainer;
import modelutil.test.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@RestController
@RequestMapping("/free-api/example")
public class ExampleRestController {

	private GridContainer example;

	@RequestMapping("/grid")
	public GridContainer getGridExample() {
		if (example == null) {
			example = Example.getExample();
		}
		return example;
	}
}