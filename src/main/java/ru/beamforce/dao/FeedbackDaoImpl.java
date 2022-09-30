package ru.beamforce.dao;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Andrey Korneychuk on 27-Apr-22
 * @version 1.0
 */
@Repository
public class FeedbackDaoImpl extends AbstractEntityManager implements FeedbackDao {

	@Override
	public int getFeedbackListSize(long authorId) {
		try {
			Session session = unwrap();
			NativeQuery query = session.createSQLQuery(
					"select count(*) from " + place("feedback") + " where author_id = :author_id"
			);
			query.setParameter("author_id", authorId);
			List resultList = query.getResultList();
			BigInteger bigInteger = ((BigInteger) resultList.get(0));
			return bigInteger.intValueExact();
		} catch (Exception e) {
			loggingDb.error(e);
		}
		return 0;
	}
}