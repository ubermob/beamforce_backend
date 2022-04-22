package ru.beamforce.dao;

import ru.beamforce.entity.GridEntity;
import ru.beamforce.entity.UserEntity;

import java.util.List;

/**
 * @author Andrey Korneychuk on 22-Apr-22
 * @version 1.0
 */
public interface GridDao {

	List<GridEntity> getGridEntityList(UserEntity user);
}