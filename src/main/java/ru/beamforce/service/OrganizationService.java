package ru.beamforce.service;

import ru.beamforce.entity.OrganizationEntity;
import ru.beamforce.entity.UserEntity;
import ru.beamforce.shortobject.Token;

/**
 * @author Andrey Korneychuk on 18-Feb-22
 * @version 1.0
 */
public interface OrganizationService {

	void createOrganization(UserEntity user, OrganizationEntity organization);

	OrganizationEntity getOrganizationWithToken(Token token);

	void newOrganizationToken(UserEntity user);

	boolean nameIsAvailable(OrganizationEntity organization);
}