package ru.beamforce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import ru.beamforce.dao.UserDao;
import ru.beamforce.dto.EmailDTO;
import ru.beamforce.dto.RegistrationUserDTO;
import ru.beamforce.dto.UpdatePasswordDTO;
import ru.beamforce.entity.OrganizationEntity;
import ru.beamforce.entity.UserEntity;
import ru.beamforce.repository.UserRepository;
import ru.beamforce.shortobject.NewUserInformer;
import ru.beamforce.shortobject.Token;

import java.security.Principal;

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
	@Autowired
	private OrganizationService organizationService;

	@Override
	public UserEntity getUserByUsername(String username) {
		return userRepository.findByName(username);
	}

	@Override
	public UserEntity getUserByPrincipal(Principal principal) {
		return getUserByUsername(principal.getName());
	}

	@Override
	public UserEntity getUserById(long id) {
		return userRepository.getById(id);
	}

	@Override
	public void deleteEmail(UserEntity user) {
		user.setEmail(null);
		userRepository.save(user);
	}

	@Override
	public void deleteUser(UserEntity user) {
		if (!user.isAdmin()) {
			userRepository.delete(user);
		}
	}

	@Override
	public void updateEmail(UserEntity user, EmailDTO emailDTO) {
		user.setEmail(emailDTO.getEmail());
		userRepository.save(user);
	}

	@Override
	public void comparePassword(UserEntity user, UpdatePasswordDTO updatePasswordDTO, Errors errors) {
		boolean isMatches = userDao.comparePasswords(user, updatePasswordDTO);
		if (!isMatches) {
			errors.rejectValue("oldPassword", "error.updatePasswordDTO"
					, "???????????? ???? ?????????????????? ?? ?????????????????????? ??????????????");
		}
	}

	@Override
	public void updatePassword(UserEntity user, UpdatePasswordDTO updatePasswordDTO) {
		userDao.updatePassword(user, updatePasswordDTO);
		userRepository.save(user);
	}

	@Override
	public void leaveOrganization(UserEntity user) {
		user.setOrganization(null);
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void joinToOrganization(UserEntity user, Token token) {
		OrganizationEntity organization = organizationService.getOrganizationWithToken(token);
		if (user.getOrganization() == null && organization != null) {
			user.setOrganization(organization);
			userRepository.save(user);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByName(username);
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