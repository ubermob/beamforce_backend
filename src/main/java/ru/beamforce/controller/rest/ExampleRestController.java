package ru.beamforce.controller.rest;

import modelutil.container.GridContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.beamforce.repository.GridRepository;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@RestController
@RequestMapping("/free-api/example")
public class ExampleRestController {

	@Autowired
	private GridRepository gridRepository;

	@CrossOrigin
	@RequestMapping("/grid")
	public GridContainer getGridExample() {
		return gridRepository.getById(1L).getGridContainer();
	}

	@CrossOrigin
	@RequestMapping("/grid-with-offsets")
	public GridContainer getGridWithOffsetsExample() {
		System.out.println("GET Example with offsets");
		return gridRepository.getById(2L).getGridContainer();
	}
}