package ru.beamforce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beamforce.bean.RandomToken;
import ru.beamforce.dao.OrganizationDao;
import ru.beamforce.entity.OrganizationEntity;
import ru.beamforce.entity.UserEntity;
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
	public void createOrganization(UserEntity user, OrganizationEntity organization) {
		organizationDao.createOrganization(user, organization);
	}

	@Override
	@Transactional
	public OrganizationEntity getOrganizationWithToken(Token token) {
		return organizationDao.getOrganizationWithToken(token);
	}

	@Override
	public void newOrganizationToken(UserEntity user) {
		if (user.getId() == user.getOrganization().getAdminId() && user.getOrganization() != null) {
			OrganizationEntity organization = user.getOrganization();
			organization.setJoinToken(randomToken.getToken());
			organizationRepository.save(organization);
		}
	}

	@Override
	public boolean nameIsAvailable(OrganizationEntity organization) {
		return organizationDao.nameIsAvailable(organization);
	}
}