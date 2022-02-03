package ru.beamforce.service;

import ru.beamforce.shortobject.RegistrationUser;
import ru.beamforce.shortobject.NewUserInformer;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
public interface RegistrationUserService {

	void createNewUser(RegistrationUser registrationUser);

	NewUserInformer getNewUserInformer(RegistrationUser registrationUser);
}