package ru.beamforce.dao;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.beamforce.entity.ModelEntity;
import ru.beamforce.logger.LoggingDb;

import java.util.List;

/**
 * @author Andrey Korneychuk on 22-Apr-22
 * @version 1.0
 */
@Repository
public class ModelDaoImpl extends AbstractEntityManager implements ModelDao {

	@Autowired
	private LoggingDb loggingDb;

	@Override
	public void incrementApiCallCounter(long modelId) {
		increment("api_call_counter", modelId);
	}

	@Override
	public void incrementViewCounter(long modelId) {
		increment("view_counter", modelId);
	}

	@Override
	public List<ModelEntity> getPersonalModelEntityList(long authorId) {
		try {
			Session session = unwrap();
			Query<ModelEntity> query = session.createQuery(
					"FROM ModelEntity WHERE authorId = :author_id"
			);
			query.setParameter("author_id", authorId);
			return query.getResultList();
		} catch (Exception e) {
			loggingDb.error(e);
		}
		return null;
	}

	@Override
	public List<ModelEntity> getOrganizationWideModelEntityList(long authorId) {
		try {
			Session session = unwrap();
			var query = session.createQuery(
					"FROM ModelEntity WHERE authorId IN (" +
							"SELECT id FROM UserEntity WHERE organization IN (" +
							"SELECT organization FROM UserEntity WHERE id = :author_id" +
							")" +
							")"
			);
			query.setParameter("author_id", authorId);
			return (List<ModelEntity>) query.getResultList();
		} catch (Exception e) {
			loggingDb.error(e);
		}
		return null;
	}

	private void increment(String fieldName, long modelId) {
		try {
			Session session = unwrap();
			NativeQuery sqlQuery = session.createSQLQuery("update " + place("models") +
					" set %s = %s + 1 where id=:id".formatted(fieldName, fieldName));
			sqlQuery.setParameter("id", modelId);
			sqlQuery.executeUpdate();
		} catch (Exception e) {
			loggingDb.error(e);
		}
	}
}