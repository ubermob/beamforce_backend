package ru.beamforce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.beamforce.modelutil.container.ForceContainer;
import ru.beamforce.modelutil.container.ForceKeys;
import ru.beamforce.modelutil.container.GridContainer;
import ru.beamforce.modelutil.container.ModelContainer;
import ru.beamforce.repository.GridRepository;
import test.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@RestController
@RequestMapping("/free-api/example")
public class ExampleRestController {

	@Autowired
	private GridRepository gridRepository;

	@RequestMapping()
	public List<String> getMap() {
		String host = "http://192.168.1.13:8080/free-api/example";
		ArrayList<String> strings = new ArrayList<>();
		strings.add(host + "/grid");
		strings.add(host + "/grid-with-offsets");
		strings.add(host + "/model");
		strings.add(host + "/model/force-keys");
		strings.add(host + "/model/force");
		return strings;
	}

	@CrossOrigin
	@RequestMapping("/grid")
	public GridContainer getGridExample() {
		return gridRepository.getById(1L).getGridContainer();
	}

	@CrossOrigin
	@RequestMapping("/grid-with-offsets")
	public GridContainer getGridWithOffsetsExample() {
		return gridRepository.getById(2L).getGridContainer();
	}

	@CrossOrigin
	@RequestMapping("/model")
	public ModelContainer getModelContainer() {
		try {
			return Example.getExampleModelContainer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@CrossOrigin
	@RequestMapping("/model/force-keys")
	public ForceKeys getForceKeys() {
		ForceKeys s = new ForceKeys();
		s.add("abc").add("def");
		return s;
	}

	@CrossOrigin
	@RequestMapping("/model/force")
	public ForceContainer getForce() {
		ForceContainer force = new ForceContainer();
		for (int i = 1; i <= 24; i++) {
			force.add(i, "abc", i * 0.23);
		}
		for (int i = 1; i <= 24; i++) {
			force.add(i, "def", i * 1.37);
		}
		return force;
	}
}