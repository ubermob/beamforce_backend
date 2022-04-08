package ru.beamforce.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.beamforce.dto.RegistrationUserDTO;
import ru.beamforce.entity.User;
import ru.beamforce.service.AdminUserService;
import ru.beamforce.service.RegistrationUserService;
import ru.beamforce.shortobject.NewUserInformer;

/**
 * @author Andrey Korneychuk on 08-Apr-22
 * @version 1.0
 */
@Component
public class AfterStartComponent {

	@Autowired
	private RegistrationUserService registrationUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AdminUserService adminUserService;

	@Order(1)
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("hello world, I have just started up");

		NewUserInformer adminInformer = registrationUserService.getNewUserInformer(
				new RegistrationUserDTO("Admin", "", "")
		);
		if (adminInformer.isAvailableName()) {
			User user = new User();
			user.setName("Admin");
			// TODO Change
			user.setPassword(passwordEncoder.encode("123"));
			user.setActive(true);
			user.setRoleUserAndAdmin();
			System.out.println();
			adminUserService.createUser(user);
			System.out.println("Admin user created");
		} else {
			System.out.println("Admin user already exist");
		}
	}
}