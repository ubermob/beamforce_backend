package ru.beamforce.service;

import modelutil.container.GridContainer;
import ru.beamforce.dto.GridInputDTO;
import ru.beamforce.entity.GridEntity;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
public interface GridService {

	void add(GridInputDTO gridInputDTO, Principal principal);

	GridEntity get(long id);

	GridContainer getGridContainer(long id);
}