package ru.beamforce.service;

import org.springframework.validation.Errors;
import ru.beamforce.dto.EmailDTO;
import ru.beamforce.dto.UpdatePasswordDTO;
import ru.beamforce.entity.UserEntity;
import ru.beamforce.shortobject.Token;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
public interface UserService {

	UserEntity getUserByUsername(String username);

	UserEntity getUserByPrincipal(Principal principal);

	UserEntity getUserById(long id);

	void deleteEmail(UserEntity user);

	void deleteUser(UserEntity user);

	void updateEmail(UserEntity user, EmailDTO emailDTO);

	void comparePassword(UserEntity user, UpdatePasswordDTO updatePasswordDTO, Errors errors);

	void updatePassword(UserEntity user, UpdatePasswordDTO updatePasswordDTO);

	void leaveOrganization(UserEntity user);

	void joinToOrganization(UserEntity user, Token token);
}