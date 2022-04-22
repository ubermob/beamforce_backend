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

	@Override
	public void add(MultipartFile geometryFile, MultipartFile reinforcementFile
			, ModelInputDTO modelInputDTO, Principal principal) {
		Stopwatch stopwatch = new Stopwatch();
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

			UserEntity user = userService.getUserByPrincipal(principal);
			ModelEntity modelEntity = new ModelEntity();
			modelEntity.setName(modelInputDTO.getName());
			modelEntity.setCommentary(modelInputDTO.getCommentary());
			modelEntity.setAuthorId(user.getId());
			modelEntity.setGridId(modelInputDTO.getGridId());
			modelEntity.setAccessLevel(modelInputDTO.getAccessLevel());
			modelEntity.setForceKeys(modelParser.getForceKeys());
			modelEntity.setModelContainer(modelParser.getModelContainer());
			modelEntity.setForceContainer(modelParser.getForceContainer());
			//
			modelRepository.save(modelEntity);
		} catch (Exception e) {
			LOGGER.info(e);
		}
		LOGGER.info("Add DONE in " + stopwatch.getPrettyString());
	}

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
}