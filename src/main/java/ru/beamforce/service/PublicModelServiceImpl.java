package ru.beamforce.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beamforce.bean.RandomToken;
import ru.beamforce.dao.PublicModelDao;
import ru.beamforce.entity.ModelEntity;
import ru.beamforce.repository.ModelRepository;
import utools.stopwatch.Stopwatch;

/**
 * @author Andrey Korneychuk on 23-Apr-22
 * @version 1.0
 */
@Service
public class PublicModelServiceImpl implements PublicModelService {

	private static final Logger LOGGER = LogManager.getLogger(PublicModelServiceImpl.class);
	@Autowired
	private PublicModelDao publicModelDao;
	@Autowired
	private RandomToken randomToken;
	@Autowired
	private ModelRepository modelRepository;

	@Override
	@Transactional
	public void makePublic(ModelEntity modelEntity) {
		Stopwatch stopwatch = new Stopwatch();
		modelEntity.setAccessLevel(ModelEntity.PUBLIC_ACCESS_LEVEL);
		if (modelEntity.getPublicAccessToken() == null) {
			boolean loop = true;
			byte publicAccessTokenLength = ModelEntity.PUBLIC_ACCESS_TOKEN_LENGTH;
			String publicToken = null;
			while (loop) {
				publicToken = randomToken.getTinyToken(publicAccessTokenLength);
				if (publicModelDao.isAvailableToken(publicToken)) {
					loop = false;
				}
			}
			modelEntity.setPublicAccessToken(publicToken);
			LOGGER.info("Generated unique public token in: " + stopwatch.getPrettyString());
		}
		modelRepository.save(modelEntity);
	}

	@Override
	public void makeNotPublic(ModelEntity model) {
	}

	@Override
	public boolean isPublicModel(String token) {
		return getModel(token).isPublicModel();
	}

	@Override
	@Transactional
	public ModelEntity getModel(String token) {
		Stopwatch stopwatch = new Stopwatch();
		ModelEntity model = publicModelDao.getModel(token);
		LOGGER.info("Get model in " + stopwatch.getPrettyString());
		return model;
	}
}