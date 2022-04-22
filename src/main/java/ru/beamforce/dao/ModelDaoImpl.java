package ru.beamforce.dao;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

/**
 * @author Andrey Korneychuk on 22-Apr-22
 * @version 1.0
 */
@Repository
public class ModelDaoImpl extends AbstractEntityManager implements ModelDao {

	@Override
	public void incrementApiCallCounter(long modelId) {
		increment("api_call_counter", modelId);
	}

	@Override
	public void incrementViewCounter(long modelId) {
		increment("view_counter", modelId);
	}

	private void increment(String fieldName, long modelId) {
		try {
			Session session = unwrap();
			NativeQuery sqlQuery = session.createSQLQuery("update " + place("models") +
					" set :fieldName = :fieldName + 1 where id=:id");
			sqlQuery.setParameter("id", modelId);
			sqlQuery.setParameter("fieldName", fieldName);
			sqlQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}