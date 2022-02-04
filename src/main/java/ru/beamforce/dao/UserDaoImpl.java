package ru.beamforce.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.beamforce.entity.User;
import ru.beamforce.repository.UserRepository;
import ru.beamforce.shortobject.NewUserInformer;
import ru.beamforce.shortobject.RegistrationUser;

import javax.persistence.EntityManager;
import java.math.BigInteger;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Value("${db.name}")
	private String dbName;
	@Value("${db.table.users}")
	private String tableUsers;


	@Override
	public void createNewUser(RegistrationUser registrationUser) {
		User user = new User();
		user.setName(registrationUser.getName());
		user.setEmail(registrationUser.getEmail());
		user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
		user.setActive(true);
		user.setRoleUser();
		System.out.println(user);
	}

	@Override
	public NewUserInformer getNewUserInformer(RegistrationUser registrationUser) {
		Session session = unwrap();
		registrationUser.trimEmail();

		var query = session.createSQLQuery(
				"select count(name) from " + place(tableUsers) + " where name=:user_name"
		);
		var resultList = query.setParameter("user_name", registrationUser.getName()).getResultList();
		boolean isAvailableName = resultList.get(0).equals(new BigInteger("0"));

		query = session.createSQLQuery(
				"select count(email) from " + place(tableUsers) + " where email=:user_email"
		);
		resultList = query.setParameter("user_email", registrationUser.getEmail()).getResultList();
		boolean isAvailableEmail = resultList.get(0).equals(new BigInteger("0"));

		NewUserInformer result = new NewUserInformer();
		result.setAvailableName(isAvailableName);
		result.setAvailableEmail(isAvailableEmail);
		return result;
	}

	private String place(String tableName) {
		return dbName + "." + tableName;
	}

	private Session unwrap() {
		return entityManager.unwrap(Session.class);
	}
}