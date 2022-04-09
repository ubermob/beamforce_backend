package ru.beamforce.tmp;

import modelutil.container.GridContainer;
import modelutil.gridparser.GridParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beamforce.dto.GridInputDTO;
import ru.beamforce.entity.GridEntity;
import ru.beamforce.repository.GridRepository;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@Service
public class GridServiceImpl implements GridService {

	@Autowired
	private GridRepository gridRepository;

	@Override
	public void add(GridInputDTO gridInputDTO) {
		GridParser gridParser = new GridParser(0, 0, 0);
		// From textarea input contains "\r"
		// Remove this
		gridParser.parse(gridInputDTO.getAlong().replace("\r", "")
				+ "\n" + gridInputDTO.getAcross().replace("\r", ""));
		gridRepository.save(new GridEntity(
				gridParser.getGridContainer()
				, gridInputDTO.getName()
				, gridInputDTO.getCommentary()
		));
	}

	@Override
	public GridEntity get(long id) {
		return gridRepository.getById(id);
	}

	@Override
	public GridContainer getGridContainer(long id) {
		return get(id).getGridContainer();
	}
}