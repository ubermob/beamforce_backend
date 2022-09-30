package ru.beamforce.dao;

import ru.beamforce.entity.OrganizationEntity;
import ru.beamforce.entity.UserEntity;
import ru.beamforce.shortobject.Token;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
public interface OrganizationDao {

	void createOrganization(UserEntity user, OrganizationEntity organization);

	OrganizationEntity getOrganizationWithToken(Token token);

	boolean nameIsAvailable(OrganizationEntity organization);
}