package ru.beamforce.controller.rest;

import modelutil.container.GridContainer;
import modelutil.container.ModelContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.beamforce.repository.GridRepository;
import test.Example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
	public Force getForce() {
		Force force = new Force();
		for (int i = 1; i <= 24; i++) {
			force.add(i, "abc", i * 0.23);
		}
		for (int i = 1; i <= 24; i++) {
			force.add(i, "def", i * 1.37);
		}
		return force;
	}

	class ForceKeys {

		private Set<String> forceKeys;

		public ForceKeys() {
			forceKeys = new HashSet<>();
		}

		public Set<String> getForceKeys() {
			return forceKeys;
		}

		public void setForceKeys(Set<String> forceKeys) {
			this.forceKeys = forceKeys;
		}

		public ForceKeys add(String forceKey) {
			forceKeys.add(forceKey);
			return this;
		}
	}

	class Force {

		private HashMap<Long, HashMap<String, Double>> forces;

		public Force() {
			forces = new HashMap<>();
		}

		public HashMap<Long, HashMap<String, Double>> getForces() {
			return forces;
		}

		public void setForces(HashMap<Long, HashMap<String, Double>> forces) {
			this.forces = forces;
		}

		public Force add(long id, String key, Double value) {
			HashMap<String, Double> stringDoubleHashMap;
			if (forces.containsKey(id)) {
				stringDoubleHashMap = forces.get(id);
			} else {
				stringDoubleHashMap = new HashMap<>();
				forces.put(id, stringDoubleHashMap);
			}
			stringDoubleHashMap.put(key, value);
			return this;
		}
	}
}