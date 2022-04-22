package ru.beamforce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.beamforce.entity.ModelEntity;
import ru.beamforce.modelutil.container.ForceContainer;
import ru.beamforce.modelutil.container.ForceKeys;
import ru.beamforce.modelutil.container.ModelContainer;
import ru.beamforce.service.ModelService;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 01-Feb-22
 * @version 1.0
 */
@RestController()
@RequestMapping("/api/models/")
public class ModelRestController {

	@Autowired
	private ModelService modelService;

	@GetMapping("/{id}")
	public ModelRecord getRaw(@PathVariable long id, Principal principal) {
		ModelEntity modelEntity = modelService.get(principal, id);
		if (modelEntity != null) {
			modelService.incrementApiCallCounter(id);
		}
		return new ModelRecord(
				modelEntity.getModelContainer()
				, modelEntity.getForceKeys()
				, modelEntity.getForceContainer()
		);
	}

	record ModelRecord(ModelContainer modelContainer, ForceKeys forceKeys, ForceContainer forceContainer) {
	}
}