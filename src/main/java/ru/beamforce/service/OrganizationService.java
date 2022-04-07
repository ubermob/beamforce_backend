package ru.beamforce.service;

import org.springframework.stereotype.Service;
import ru.beamforce.entity.Organization;
import ru.beamforce.entity.User;
import ru.beamforce.shortobject.Token;

/**
 * @author Andrey Korneychuk on 18-Feb-22
 * @version 1.0
 */
public interface OrganizationService {

	void createOrganization(User user, Organization organization);

	Organization getOrganizationWithToken(Token token);

	void newOrganizationToken(User user);

	boolean nameIsUnique(Organization organization);
}