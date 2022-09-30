package ru.beamforce.dao;

import ru.beamforce.entity.ModelEntity;

import java.util.List;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
public interface ModelDao {

	void incrementApiCallCounter(long modelId);

	void incrementViewCounter(long modelId);

	List<ModelEntity> getPersonalModelEntityList(long authorId);

	List<ModelEntity> getOrganizationWideModelEntityList(long userId);
}