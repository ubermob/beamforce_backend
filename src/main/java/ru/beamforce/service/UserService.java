package ru.beamforce.service;

import org.springframework.validation.Errors;
import ru.beamforce.dto.EmailDTO;
import ru.beamforce.dto.UpdatePasswordDTO;
import ru.beamforce.entity.User;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
public interface UserService {

	User getUserByUsername(String username);

	User getUserByPrincipal(Principal principal);

	void deleteEmail(User user);

	void deleteUser(User user);

	void updateEmail(User user, EmailDTO emailDTO);

	void comparePassword(User user, UpdatePasswordDTO updatePasswordDTO, Errors errors);

	void updatePassword(User user, UpdatePasswordDTO updatePasswordDTO);

	void leaveOrganization(User user);
}