package ru.beamforce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.beamforce.bean.RandomToken;
import ru.beamforce.entity.Organization;
import ru.beamforce.entity.User;
import ru.beamforce.repository.UserRepository;
import ru.beamforce.service.UserService;

/**
 * @author Andrey Korneychuk on 18-Feb-22
 * @version 1.0
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void createOrganization(User user, Organization organization) {
		var token = new RandomToken();
		organization.setJoinToken(token.getToken(42));
		organization.setAdminId(user.getId());
		user.setOrganization(organization);
		userRepository.save(user);
	}
}