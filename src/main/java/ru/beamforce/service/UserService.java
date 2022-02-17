package ru.beamforce.service;

import ru.beamforce.dto.EmailDTO;
import ru.beamforce.entity.User;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
public interface UserService {

	User getUserByUsername(String username);

	void deleteEmail(User user);

	void deleteUser(User user);

	void updateEmail(User user, EmailDTO emailDTO);
}