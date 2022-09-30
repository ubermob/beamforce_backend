package ru.beamforce.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.beamforce.entity.ModelEntity;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Andrey Korneychuk on 23-Apr-22
 * @version 1.0
 */
@Repository
public class PublicModelDaoImpl extends AbstractEntityManager implements PublicModelDao {

	@Override
	public boolean isPublicModel(String token) {
		ModelEntity modelEntity = getModel(token);
		if (modelEntity != null) {
			return modelEntity.getAccessLevel() == ModelEntity.PUBLIC_ACCESS_LEVEL;
		}
		return false;
	}

	@Override
	public ModelEntity getModel(String token) {
		try {
			Session session = unwrap();
			Query<ModelEntity> query = session.createQuery(
					"from ModelEntity where publicAccessToken = :token"
					, ModelEntity.class
			);
			query.setParameter("token", token);
			List<ModelEntity> resultList = query.getResultList();
			if (resultList.size() > 1) {
				throw new Exception("Result list size must be 0 or 1, now: %d, token:\"%s\"".formatted(resultList.size(), token));
			}
			if (resultList.size() == 1) {
				return resultList.get(0);
			}
			return null;
		} catch (Exception e) {
			loggingDb.error(e);
		}
		return null;
	}

	@Override
	public boolean isAvailableToken(String token) {
		Session session = unwrap();

		var query = session.createSQLQuery(
				"select count(public_access_token) from " + place("models")
						+ " where public_access_token = :token"
		);
		var resultList = query.setParameter("token", token).getResultList();
		boolean isAvailableToken = resultList.get(0).equals(new BigInteger("0"));

		return isAvailableToken;
	}
}