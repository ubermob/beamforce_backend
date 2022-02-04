package ru.beamforce.service;

import ru.beamforce.dto.RegistrationUserDTO;
import ru.beamforce.shortobject.NewUserInformer;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
public interface RegistrationUserService {

	void createNewUser(RegistrationUserDTO registrationUser);

	NewUserInformer getNewUserInformer(RegistrationUserDTO registrationUser);
}