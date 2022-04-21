package ru.beamforce.controller.rest;

import modelutil.container.GridContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.beamforce.service.GridService;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 20-Apr-22
 * @version 1.0
 */
@RestController
@RequestMapping("/api/grids")
public class GridRestController {

	@Autowired
	private GridService gridService;

	@GetMapping("/{id}")
	public GridContainer getGrid(@PathVariable long id, Principal principal) {
		return gridService.getGridEntity(principal, id).getGridContainer();
	}
}