package ru.beamforce.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.beamforce.shortobject.RegistrationUser;
import ru.beamforce.shortobject.NewUserInformer;

import javax.persistence.EntityManager;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public void createNewUser(RegistrationUser registrationUser) {
		Session session = unwrap();
		//TODO
	}

	@Override
	public NewUserInformer getNewUserInformer(RegistrationUser registrationUser) {
		Session session = unwrap();
		session.createSQLQuery("insert into beamforce_db.test (str) values (:string)")
				.setParameter("string", "abcdef").executeUpdate();
		NewUserInformer result = new NewUserInformer();
		result.setAvailableName(false);
		result.setAvailableEmail(false);
		return result;
	}

	public Session unwrap() {
		return entityManager.unwrap(Session.class);
	}
}