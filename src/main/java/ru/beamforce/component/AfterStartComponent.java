package ru.beamforce.component;

import modelutil.test.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.beamforce.dto.RegistrationUserDTO;
import ru.beamforce.entity.GridEntity;
import ru.beamforce.entity.UserEntity;
import ru.beamforce.repository.GridRepository;
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
	@Autowired
	private GridRepository gridRepository;

	@Order(0)
	@EventListener(ApplicationReadyEvent.class)
	public void orderZeroAnnounce() {
		print(getClass().getSimpleName() + " start");
	}

	@Order(1)
	@EventListener(ApplicationReadyEvent.class)
	public void checkAdmin() {
		print("Checking Admin existing");
		NewUserInformer adminInformer = registrationUserService.getNewUserInformer(
				new RegistrationUserDTO("Admin", "", "")
		);
		if (adminInformer.isAvailableName()) {
			UserEntity user = new UserEntity();
			user.setName("Admin");
			// TODO Change
			String password = "123";
			user.setPassword(passwordEncoder.encode(password));
			user.setActive(true);
			user.setRoleUserAndAdmin();
			adminUserService.createUser(user);
			print("Admin user created, nickname: %s, password: %s (DO NOT FORGET CHANGE IT)"
					.formatted(user.getName(), password));
		} else {
			print("Admin user already exist");
		}
	}

	@Order(2)
	@EventListener(ApplicationReadyEvent.class)
	public void checkExampleGrid() {
		print("Checking example grid");
		if (gridRepository.existsById(1L)) {
			print("Example grid already exist");
		} else {
			// TODO saved entity can have ID != 1
			GridEntity gridEntity = new GridEntity();
			gridEntity.setId(1L);
			gridEntity.setGridContainer(Example.getExample());
			gridEntity.setName("Example");
			gridEntity.setCommentary("Example");
			gridRepository.save(gridEntity);
			print("Example grid created");
		}
	}

	private void print(String string) {
		System.out.println(string);
	}
}