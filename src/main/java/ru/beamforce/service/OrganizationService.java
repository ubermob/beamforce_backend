package ru.beamforce.service;

import org.springframework.stereotype.Service;
import ru.beamforce.entity.Organization;
import ru.beamforce.entity.User;

/**
 * @author Andrey Korneychuk on 18-Feb-22
 * @version 1.0
 */
@Service
public interface OrganizationService {

	void createOrganization(User user, Organization organization);
}