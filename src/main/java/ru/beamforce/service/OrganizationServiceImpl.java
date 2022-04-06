package ru.beamforce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beamforce.bean.RandomToken;
import ru.beamforce.dao.OrganizationDao;
import ru.beamforce.entity.Organization;
import ru.beamforce.entity.User;
import ru.beamforce.repository.OrganizationRepository;
import ru.beamforce.shortobject.Token;

/**
 * @author Andrey Korneychuk on 18-Feb-22
 * @version 1.0
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private RandomToken randomToken;

	@Override
	public void createOrganization(User user, Organization organization) {
		organizationDao.createOrganization(user, organization);
	}

	@Override
	@Transactional
	public Organization getOrganizationWithToken(Token token) {
		return organizationDao.getOrganizationWithToken(token);
	}

	@Override
	public void newOrganizationToken(User user) {
		if (user.getId().equals(user.getOrganization().getAdminId()) && user.getOrganization() != null) {
			Organization organization = user.getOrganization();
			organization.setJoinToken(randomToken.getToken());
			organizationRepository.save(organization);
		}
	}

	@Override
	public boolean nameIsUnique(Organization organization) {
		return organizationDao.nameIsUnique(organization);
	}
}