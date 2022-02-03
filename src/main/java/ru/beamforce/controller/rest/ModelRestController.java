package ru.beamforce.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.beamforce.restentity.helloMessage;

/**
 * @author Andrey Korneychuk on 01-Feb-22
 * @version 1.0
 */
@RestController
public class ModelRestController {

	@RequestMapping("/api/hello")
	public helloMessage main() {
		return new helloMessage("hello from API");
	}
}