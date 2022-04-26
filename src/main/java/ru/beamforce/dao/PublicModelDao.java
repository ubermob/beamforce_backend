package ru.beamforce.dao;

import ru.beamforce.entity.ModelEntity;

/**
 * @author Andrey Korneychuk on 23-Apr-22
 * @version 1.0
 */
public interface PublicModelDao {

	@Deprecated
	boolean isPublicModel(String token);

	ModelEntity getModel(String token);

	boolean isAvailableToken(String token);
}