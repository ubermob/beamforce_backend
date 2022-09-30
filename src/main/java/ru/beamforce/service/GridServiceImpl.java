package ru.beamforce.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beamforce.dao.GridDao;
import ru.beamforce.dto.GridInputDTO;
import ru.beamforce.entity.GridEntity;
import ru.beamforce.entity.UserEntity;
import ru.beamforce.modelutil.container.GridContainer;
import ru.beamforce.modelutil.gridparser.GridParser;
import ru.beamforce.repository.GridRepository;

import java.security.Principal;
import java.util.List;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@Service
public class GridServiceImpl implements GridService {

	private static final Logger LOGGER = LogManager.getLogger(GridServiceImpl.class);
	@Autowired
	private GridRepository gridRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private GridDao gridDao;

	@Override
	public void add(GridInputDTO gridInputDTO, Principal principal) {
		UserEntity user = userService.getUserByPrincipal(principal);
		List<GridEntity> gridList = getGridList(principal);
		if (isUserInStorageLimit(user, gridList.size())) {
			GridParser gridParser = new GridParser(
					gridInputDTO.getStartOffsetX()
					, gridInputDTO.getStartOffsetY()
					, gridInputDTO.getOffset()
			);
			gridParser.setInfoListener(s -> LOGGER.info(s));
			gridParser.setExceptionListener((s, e) -> {
				LOGGER.error(s);
				LOGGER.error(e);
			});
			// From textarea input contains "\r"
			// Remove this
			gridParser.parse(gridInputDTO.getAlong().replace("\r", "")
					+ "\n" + gridInputDTO.getAcross().replace("\r", ""));
			gridRepository.save(new GridEntity(
					gridParser.getGridContainer()
					, user.getId()
					, gridInputDTO.getName()
					, gridInputDTO.getCommentary()
			));
		}
	}

	@Override
	public GridEntity get(long gridId) {
		return gridRepository.getById(gridId);
	}

	@Override
	public GridContainer getGridContainer(long gridId) {
		return get(gridId).getGridContainer();
	}

	@Override
	public GridEntity getGridEntity(Principal principal, long gridId) {
		if (isPrincipalTheAuthorOfGrid(principal, gridId)) {
			return gridRepository.getById(gridId);
		}
		return null;
	}

	@Override
	@Transactional
	public List<GridEntity> getGridList(Principal principal) {
		return gridDao.getGridEntityList(userService.getUserByPrincipal(principal));
	}

	@Override
	public void clone(Principal principal, long gridId) {
		if (isPrincipalTheAuthorOfGrid(principal, gridId)
				&& isUserInStorageLimit(userService.getUserByPrincipal(principal), getGridList(principal).size())) {
			GridEntity original = gridRepository.getById(gridId);
			GridEntity clone = new GridEntity();
			clone.setAuthorId(original.getAuthorId());
			clone.setName(original.getName());
			clone.setCommentary(original.getCommentary());
			clone.setGridContainer(original.getGridContainer());
			gridRepository.save(clone);
		}
	}

	@Override
	public void delete(Principal principal, long gridId) {
		if (isPrincipalTheAuthorOfGrid(principal, gridId)) {
			gridRepository.deleteById(gridId);
		}
	}

	private boolean isPrincipalTheAuthorOfGrid(Principal principal, long gridId) {
		return userService.getUserByPrincipal(principal).getId() == get(gridId).getAuthorId();
	}

	private boolean isUserInStorageLimit(UserEntity user, int listSize) {
		if (user.hasUserRole()) {
			return listSize < Limit.USER_GRID_LIMIT;
		}
		return true;
	}
}