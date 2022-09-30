package ru.beamforce.service;

import org.springframework.web.multipart.MultipartFile;
import ru.beamforce.dto.ModelInputDTO;
import ru.beamforce.entity.ModelEntity;

import java.security.Principal;
import java.util.List;

/**
 * @author Andrey Korneychuk on 22-Apr-22
 * @version 1.0
 */
public interface ModelService {

	void add(MultipartFile geometryFile, MultipartFile reinforcementFile
			, ModelInputDTO modelInputDTO, Principal principal);

	ModelEntity get(Principal principal, long modelId);

	void incrementApiCallCounter(long modelId);

	void incrementViewCounter(long modelId);

	List<ModelEntity> getPersonalModelEntityList(Principal principal);

	List<ModelEntity> getOrganizationWideModelEntityList(Principal principal);

	void clone(Principal principal, long modelId);

	void delete(Principal principal, long modelId);

	void setAccessLevel(long modelId, byte accessLevel, Principal principal);
}