package ru.beamforce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beamforce.dao.OrganizationDao;
import ru.beamforce.entity.Organization;
import ru.beamforce.entity.User;

/**
 * @author Andrey Korneychuk on 18-Feb-22
 * @version 1.0
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDao organizationDao;

	@Override
	public void createOrganization(User user, Organization organization) {
		organizationDao.createOrganization(user, organization);
	}
}