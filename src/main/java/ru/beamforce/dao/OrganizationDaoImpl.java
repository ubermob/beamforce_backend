package ru.beamforce.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.beamforce.bean.RandomToken;
import ru.beamforce.entity.Organization;
import ru.beamforce.entity.User;
import ru.beamforce.repository.UserRepository;
import ru.beamforce.shortobject.Token;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Andrey Korneychuk on 18-Feb-22
 * @version 1.0
 */
@Repository
public class OrganizationDaoImpl extends AbstractEntityManager implements OrganizationDao {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RandomToken randomToken;
	@Value("${db.table.organizations}")
	private String tableOrganizations;

	@Override
	public void createOrganization(User user, Organization organization) {
		organization.setJoinToken(randomToken.getToken());
		organization.setAdminId(user.getId());
		user.setOrganization(organization);
		userRepository.save(user);
	}

	@Override
	public Organization getOrganizationWithToken(Token token) {
		Session session = unwrap();

		var query = session.createNativeQuery(
				"select * from " + place(tableOrganizations) + " where join_token=:token"
				, Organization.class
		);
		var resultList = (List<Organization>) query.setParameter("token", token.getToken()).getResultList();

		// list size must be == 1
		if (resultList.size() == 1) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean nameIsUnique(Organization organization) {
		Session session = unwrap();

		var query = session.createSQLQuery(
				"select count(name) from " + place(tableOrganizations) + " where name=:org_name"
		);
		var resultList = query.setParameter("org_name", organization.getName()).getResultList();
		boolean isUniqueName = resultList.get(0).equals(new BigInteger("0"));

		return isUniqueName;
	}
}