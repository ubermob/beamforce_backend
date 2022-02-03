package ru.beamforce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beamforce.dao.UserDao;
import ru.beamforce.shortobject.RegistrationUser;
import ru.beamforce.shortobject.NewUserInformer;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
@Service
public class RegistrationUserServiceImpl implements RegistrationUserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void createNewUser(RegistrationUser registrationUser) {
		userDao.createNewUser(registrationUser);
	}

	@Override
	@Transactional
	public NewUserInformer getNewUserInformer(RegistrationUser registrationUser) {
		return userDao.getNewUserInformer(registrationUser);
	}
}