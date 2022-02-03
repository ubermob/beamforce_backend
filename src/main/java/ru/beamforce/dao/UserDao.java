package ru.beamforce.dao;

import ru.beamforce.shortobject.RegistrationUser;
import ru.beamforce.shortobject.NewUserInformer;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
public interface UserDao {

	void createNewUser(RegistrationUser registrationUser);

	NewUserInformer getNewUserInformer(RegistrationUser registrationUser);
}