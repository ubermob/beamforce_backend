package ru.beamforce.service;

import modelutil.container.GridContainer;
import ru.beamforce.dto.GridInputDTO;
import ru.beamforce.entity.GridEntity;

import java.security.Principal;
import java.util.List;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
public interface GridService {

	void add(GridInputDTO gridInputDTO, Principal principal);

	GridEntity get(long gridId);

	GridContainer getGridContainer(long gridId);

	GridEntity getGridEntity(Principal principal, long gridId);

	List<GridEntity> getGridList(Principal principal);

	void clone(Principal principal, long gridId);

	void delete(Principal principal, long gridId);
}