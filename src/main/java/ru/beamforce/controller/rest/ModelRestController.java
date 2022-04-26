package ru.beamforce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.beamforce.entity.ModelEntity;
import ru.beamforce.modelutil.container.ForceContainer;
import ru.beamforce.modelutil.container.ForceKeys;
import ru.beamforce.modelutil.container.ModelContainer;
import ru.beamforce.service.ModelService;
import ru.beamforce.service.PublicModelService;

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
	@Autowired
	private PublicModelService publicModelService;

	@GetMapping("/{id}")
	public ModelEntity getRawById(@PathVariable long id, Principal principal
			, @RequestParam(name = "viewer", required = false) String viewer) {
		ModelEntity modelEntity = modelService.get(principal, id);
		if (modelEntity != null && !isViewer(viewer)) {
			modelService.incrementApiCallCounter(id);
		}
		if (modelEntity != null && isViewer(viewer)) {
			modelService.incrementViewCounter(id);
		}
		return modelEntity;
	}

	@GetMapping("/token/{token}")
	public ModelEntity getRawByToken(@PathVariable String token, Principal principal
			, @RequestParam(name = "viewer", required = false) String viewer) {
		ModelEntity modelEntity = publicModelService.getModel(token);
		if (modelEntity != null && !isViewer(viewer)) {
			modelService.incrementApiCallCounter(modelEntity.getId());
		}
		if (modelEntity != null && isViewer(viewer)) {
			modelService.incrementViewCounter(modelEntity.getId());
		}
		return modelEntity;
	}

	@GetMapping("/short/{id}")
	public ModelRecord getShortRaw(@PathVariable long id, Principal principal
			, @RequestParam(name = "viewer", required = false) String viewer) {
		ModelEntity modelEntity = modelService.get(principal, id);
		if (modelEntity != null && !isViewer(viewer)) {
			modelService.incrementApiCallCounter(id);
		}
		if (modelEntity != null && isViewer(viewer)) {
			modelService.incrementViewCounter(id);
		}
		return new ModelRecord(
				modelEntity.getModelContainer()
				, modelEntity.getForceKeys()
				, modelEntity.getForceContainer()
		);
	}

	record ModelRecord(ModelContainer modelContainer, ForceKeys forceKeys, ForceContainer forceContainer) {
	}

	private boolean isViewer(String viewer) {
		if (viewer == null) {
			return false;
		}
		return viewer.equals("true");
	}
}