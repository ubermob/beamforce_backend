package ru.beamforce.service;

import ru.beamforce.entity.ModelEntity;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 22-Apr-22
 * @version 1.0
 */
public interface PublicModelService {

	void makePublic(ModelEntity model);

	void makeNotPublic(ModelEntity model);

	boolean isPublicModel(String token);

	ModelEntity getModel(String token);
}