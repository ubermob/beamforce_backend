package ru.beamforce.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.beamforce.dao.ModelDao;
import ru.beamforce.dto.ModelInputDTO;
import ru.beamforce.entity.ModelEntity;
import ru.beamforce.entity.UserEntity;
import ru.beamforce.modelutil.modelparser.ModelParser;
import ru.beamforce.repository.ModelRepository;
import utools.stopwatch.Stopwatch;

import java.security.Principal;
import java.util.List;

/**
 * @author Andrey Korneychuk on 22-Apr-22
 * @version 1.0
 */
@Service
public class ModelServiceImpl implements ModelService {

	private static final Logger LOGGER = LogManager.getLogger(ModelServiceImpl.class);
	@Autowired
	private UserService userService;
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private ModelDao modelDao;
	@Autowired
	private PublicModelService publicModelService;

	@Override
	public void add(MultipartFile geometryFile, MultipartFile reinforcementFile
			, ModelInputDTO modelInputDTO, Principal principal) {
		UserEntity user = userService.getUserByPrincipal(principal);
		List<ModelEntity> personalModelEntityList = getPersonalModelEntityList(principal);
		if (isUserInStorageLimit(user, personalModelEntityList.size())) {
			Stopwatch stopwatch = new Stopwatch();
			byte stage = 1;
			if (geometryFile != null) {
				stage++;
			}
			if (reinforcementFile != null) {
				stage++;
			}
			try {
				ModelParser modelParser = new ModelParser();
				modelParser.consumeWorkbook(WorkbookFactory.create(geometryFile.getInputStream()));
				modelParser.consumeWorkbook(WorkbookFactory.create(reinforcementFile.getInputStream()));
				modelParser.setInfoListener(x -> LOGGER.info(x));
				modelParser.addInfoListenerForNodeParser(x -> LOGGER.info(x));
				modelParser.addInfoListenerForBarParser(x -> LOGGER.info(x));
				modelParser.addInfoListenerForModelReinforcementParser(x -> LOGGER.info(x));
				modelParser.setExceptionListener((s, e) -> {
					LOGGER.error(s);
					LOGGER.error(e);
				});
				modelParser.addExceptionListenerForNodeParser((s, e) -> {
					LOGGER.error(s);
					LOGGER.error(e);
				});
				modelParser.addExceptionListenerForBarParser((s, e) -> {
					LOGGER.error(s);
					LOGGER.error(e);
				});
				modelParser.addExceptionListenerForModelReinforcementParser((s, e) -> {
					LOGGER.error(s);
					LOGGER.error(e);
				});

				modelParser.du();

				ModelEntity modelEntity = new ModelEntity();
				modelEntity.setName(modelInputDTO.getName());
				modelEntity.setCommentary(modelInputDTO.getCommentary());
				modelEntity.setAuthorId(user.getId());
				modelEntity.setGridId(modelInputDTO.getGridId());
				modelEntity.setAccessLevel(modelInputDTO.getAccessLevel());
				modelEntity.setForceKeys(modelParser.getForceKeys());
				modelEntity.setModelContainer(modelParser.getModelContainer());
				modelEntity.setForceContainer(modelParser.getForceContainer());
				modelEntity.setNodesNumber(modelParser.getNodeNumber());
				modelEntity.setElementsNumber(modelParser.getBarNumber());

				modelRepository.save(modelEntity);
			} catch (Exception e) {
				LOGGER.info(e);
			}
			LOGGER.info("Add DONE in " + stopwatch.getPrettyString());
		}
	}

	/**
	 * Return {@link ModelEntity} instance if user is author.
	 *
	 * @param principal Spring security principal
	 * @param modelId   model id
	 * @return instance of Model Entity
	 */
	@Override
	public ModelEntity get(Principal principal, long modelId) {
		ModelEntity modelEntity = modelRepository.getById(modelId);
		if (userService.getUserByPrincipal(principal).getId() == modelEntity.getAuthorId()) {
			return modelEntity;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void incrementApiCallCounter(long modelId) {
		modelDao.incrementApiCallCounter(modelId);
	}

	@Override
	@Transactional
	public void incrementViewCounter(long modelId) {
		modelDao.incrementViewCounter(modelId);
	}

	@Override
	@Transactional
	public List<ModelEntity> getPersonalModelEntityList(Principal principal) {
		return modelDao.getPersonalModelEntityList(userService.getUserByPrincipal(principal).getId());
	}

	@Override
	@Transactional
	public List<ModelEntity> getOrganizationWideModelEntityList(Principal principal) {
		return modelDao.getOrganizationWideModelEntityList(userService.getUserByPrincipal(principal).getId());
	}

	@Override
	public void clone(Principal principal, long modelId) {
		ModelEntity original = get(principal, modelId);
		if (original != null
				&& isUserInStorageLimit(
				userService.getUserByPrincipal(principal)
				, getPersonalModelEntityList(principal).size()
		)) {
			ModelEntity clone = new ModelEntity();
			clone.setAuthorId(original.getAuthorId());
			clone.setName(original.getName());
			clone.setCommentary(original.getCommentary());
			clone.setAccessLevel(original.getAccessLevel());
			clone.setForceKeys(original.getForceKeys());
			clone.setModelContainer(original.getModelContainer());
			clone.setForceContainer(original.getForceContainer());
			clone.setStage(original.getStage());
			clone.setElementsNumber(original.getElementsNumber());
			clone.setNodesNumber(original.getNodesNumber());
			clone.setGridId(original.getGridId());
			modelRepository.save(clone);
		}
	}

	@Override
	public void delete(Principal principal, long modelId) {
		ModelEntity model = get(principal, modelId);
		if (model != null) {
			modelRepository.deleteById(modelId);
		}
	}

	@Override
	public void setAccessLevel(long modelId, byte accessLevel, Principal principal) {
		ModelEntity model = get(principal, modelId);
		if (model != null) {
			if (model.getAccessLevel() != ModelEntity.PUBLIC_ACCESS_LEVEL && accessLevel == ModelEntity.PUBLIC_ACCESS_LEVEL) {
				publicModelService.makePublic(model);
			}
			if (model.getAccessLevel() == ModelEntity.PUBLIC_ACCESS_LEVEL && accessLevel != ModelEntity.PUBLIC_ACCESS_LEVEL) {
				publicModelService.makeNotPublic(model);
			}
			model.setAccessLevel(accessLevel);
			modelRepository.save(model);
		}
	}

	private boolean isUserInStorageLimit(UserEntity user, int listSize) {
		if (user.hasUserRole()) {
			return listSize < Limit.USER_MODEL_LIMIT;
		}
		return true;
	}
}