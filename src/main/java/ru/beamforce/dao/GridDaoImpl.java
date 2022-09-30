package ru.beamforce.dao;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import ru.beamforce.entity.GridEntity;
import ru.beamforce.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Korneychuk on 19-Apr-22
 * @version 1.0
 */
@Repository
public class GridDaoImpl extends AbstractEntityManager implements GridDao {

	@Override
	public List<GridEntity> getGridEntityList(UserEntity user) {
		try {
			Session session = unwrap();
			NativeQuery<GridEntity> sqlQuery = session.createNativeQuery(
					"select * from " + place("grids") + " where author_id = :user_id", GridEntity.class
			);
			sqlQuery.setParameter("user_id", user.getId());
			return sqlQuery.getResultList();
		} catch (Exception e) {
			loggingDb.error(e);
		}
		return new ArrayList<>();
	}
}