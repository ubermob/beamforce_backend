package ru.beamforce.dao;

import ru.beamforce.entity.Organization;
import ru.beamforce.entity.User;
import ru.beamforce.shortobject.Token;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
public interface OrganizationDao {

	void createOrganization(User user, Organization organization);

	Organization getOrganizationWithToken(Token token);

	boolean nameIsUnique(Organization organization);
}