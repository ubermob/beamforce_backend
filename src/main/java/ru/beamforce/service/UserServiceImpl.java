package ru.beamforce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beamforce.dao.UserDao;
import ru.beamforce.dto.EmailDTO;
import ru.beamforce.dto.RegistrationUserDTO;
import ru.beamforce.entity.User;
import ru.beamforce.repository.UserRepository;
import ru.beamforce.shortobject.NewUserInformer;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService, RegistrationUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByUsername(String username) {
		User user = userRepository.findByName(username);
		return user;
	}

	@Override
	public void deleteEmail(User user) {
		user.setEmail(null);
		userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public void updateEmail(User user, EmailDTO emailDTO) {
		user.setEmail(emailDTO.getEmail());
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

	@Override
	@Transactional
	public void createNewUser(RegistrationUserDTO registrationUser) {
		userDao.createNewUser(registrationUser);
	}

	@Override
	@Transactional
	public NewUserInformer getNewUserInformer(RegistrationUserDTO registrationUser) {
		return userDao.getNewUserInformer(registrationUser);
	}
}