package ru.beamforce.dao;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.beamforce.entity.ModelEntity;

import java.util.ArrayList;
import java.util.List;

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
		return new ArrayList<>();
	}

	// TODO
	@Override
	public List<ModelEntity> getOrganizationWideModelEntityList(long userId) {
		try {
/*			Session session = unwrap();
			var query = session.createQuery(
					"FROM ModelEntity WHERE id IN (" +
							"SELECT id FROM ModelEntity WHERE authorId IN (" +
							"SELECT id FROM UserEntity WHERE organization.id = (" +
							"SELECT organization.id FROM UserEntity WHERE id = :user_id" +
							")" +
							")" +
							") AND accessLevel > :private_access_level OR id IN (" +
							"SELECT id FROM ModelEntity WHERE authorId)" +
							"FROM ModelEntity WHERE id = :user_id"
			);
			query.setParameter("user_id", userId);
			query.setParameter("private_access_level", ModelEntity.PRIVATE_ACCESS_LEVEL);
			return (List<ModelEntity>) query.getResultList();*/
		} catch (Exception e) {
			loggingDb.error(e);
		}
		return new ArrayList<>();
	}

	private void increment(String fieldName, long modelId) {
		try {
			Session session = unwrap();
			NativeQuery sqlQuery = session.createSQLQuery("UPDATE " + place("models") +
					" SET %s = %s + 1 WHERE id = :id".formatted(fieldName, fieldName));
			sqlQuery.setParameter("id", modelId);
			sqlQuery.executeUpdate();
		} catch (Exception e) {
			loggingDb.error(e);
		}
	}
}