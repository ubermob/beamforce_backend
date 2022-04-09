package ru.beamforce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beamforce.dao.UserDao;
import ru.beamforce.entity.UserEntity;

/**
 * @author Andrey Korneychuk on 08-Apr-22
 * @version 1.0
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void createUser(UserEntity user) {
		userDao.createNewUser(user);
	}
}