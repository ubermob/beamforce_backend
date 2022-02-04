package ru.beamforce.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
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
	@Value("${db.name}")
	private String dbName;
	@Value("${db.table.default_spring_users}")
	private String defaultSpringUsers;
	@Value("${db.table.default_spring_authorities}")
	private String defaultSpringAuthorities;
	@Value("${db.table.users}")
	private String tableUsers;
	@Value("${user.standart_role}")
	private String userRole;

	@Override
	public void createNewUser(RegistrationUser registrationUser) {
		Session session = unwrap();
		registrationUser.trimEmail();

		var query = session.createSQLQuery(
				"insert into " + place(defaultSpringUsers)
						+ " (username, password, enabled) values(:user_name, :password, :enabled)"
		);
		query.setParameter("user_name", registrationUser.getName())
				.setParameter("password", "{bcrypt}" + passwordEncoder.encode(registrationUser.getPassword()))
				.setParameter("enabled", 1);
		query.executeUpdate();

		query = session.createSQLQuery(
				"insert into " + place(defaultSpringAuthorities)
						+ "(username, authority) values(:user_name, :authority)"
		);
		query.setParameter("user_name", registrationUser.getName())
				.setParameter("authority", userRole);
		query.executeUpdate();

		query = session.createSQLQuery(
				"select max(id) from " + place(tableUsers)
		);
		int id = ((int) query.getResultList().get(0)) + 1;

		if (registrationUser.getEmail() == null) {
			query = session.createSQLQuery(
					"insert into " + place(tableUsers) + " (user, id) values(:user, :id)"
			);
			query.setParameter("user", registrationUser.getName())
					.setParameter("id", id);
			query.executeUpdate();
		} else {
			query = session.createSQLQuery(
					"insert into " + place(tableUsers) + " (user, id, email) values(:user, :id, :email)"
			);
			query.setParameter("user", registrationUser.getName())
					.setParameter("id", id)
					.setParameter("email", registrationUser.getEmail());
			query.executeUpdate();
		}
	}

	@Override
	public NewUserInformer getNewUserInformer(RegistrationUser registrationUser) {
		Session session = unwrap();
		registrationUser.trimEmail();

		var query = session.createSQLQuery(
				"select count(user) from " + place(tableUsers) + " where user=:user_name"
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