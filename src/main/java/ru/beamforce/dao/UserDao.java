package ru.beamforce.dao;

import ru.beamforce.entity.RegistrationUser;
import ru.beamforce.informer.NewUserInformer;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
public interface UserDao {

	void createNewUser(RegistrationUser registrationUser);

	NewUserInformer getNewUserInformer(RegistrationUser registrationUser);
}