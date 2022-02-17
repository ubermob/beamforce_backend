package ru.beamforce.dao;

import ru.beamforce.dto.UpdatePasswordDTO;
import ru.beamforce.entity.User;
import ru.beamforce.shortobject.NewUserInformer;
import ru.beamforce.dto.RegistrationUserDTO;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
public interface UserDao {

	void createNewUser(RegistrationUserDTO registrationUser);

	NewUserInformer getNewUserInformer(RegistrationUserDTO registrationUser);

	boolean comparePasswords(User user, UpdatePasswordDTO updatePasswordDTO);

	void updatePassword(User user, UpdatePasswordDTO updatePasswordDTO);
}