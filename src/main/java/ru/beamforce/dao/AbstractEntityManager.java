package ru.beamforce.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.beamforce.logger.LoggingDb;

import javax.persistence.EntityManager;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
public abstract class AbstractEntityManager {

	@Autowired
	protected EntityManager entityManager;
	@Value("${db.name}")
	private String dbName;
	@Autowired
	protected LoggingDb loggingDb;

	protected String place(String tableName) {
		return dbName + "." + tableName;
	}

	protected Session unwrap() {
		return entityManager.unwrap(Session.class);
	}
}