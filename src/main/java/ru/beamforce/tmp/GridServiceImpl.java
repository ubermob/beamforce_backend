package ru.beamforce.tmp;

import modelutil.container.GridContainer;
import modelutil.gridparser.GridParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beamforce.dto.GridInputDTO;
import ru.beamforce.entity.GridEntity;
import ru.beamforce.repository.GridRepository;
import ru.beamforce.service.UserService;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@Service
public class GridServiceImpl implements GridService {

	@Autowired
	private GridRepository gridRepository;
	@Autowired
	private UserService userService;

	@Override
	public void add(GridInputDTO gridInputDTO, Principal principal) {
		GridParser gridParser = new GridParser(
				gridInputDTO.getStartOffsetX()
				, gridInputDTO.getStartOffsetY()
				, gridInputDTO.getOffset()
		);
		// From textarea input contains "\r"
		// Remove this
		gridParser.parse(gridInputDTO.getAlong().replace("\r", "")
				+ "\n" + gridInputDTO.getAcross().replace("\r", ""));
		gridRepository.save(new GridEntity(
				gridParser.getGridContainer()
				, userService.getUserByUsername(principal.getName()).getId()
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