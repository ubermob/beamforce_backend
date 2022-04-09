package ru.beamforce.service;

import ru.beamforce.entity.UserEntity;

/**
 * @author Andrey Korneychuk on 08-Apr-22
 * @version 1.0
 */
public interface AdminUserService {

	void createUser(UserEntity user);
}