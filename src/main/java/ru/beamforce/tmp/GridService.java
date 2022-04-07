package ru.beamforce.tmp;

import modelutil.container.GridContainer;
import org.springframework.stereotype.Service;
import ru.beamforce.dto.GridInputDTO;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
public interface GridService {

	void add(GridInputDTO gridInputDTO);

	GridContainer get(int id);
}