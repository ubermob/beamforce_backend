package ru.beamforce.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.beamforce.bean.PoCServerDriver;
import ru.beamforce.dto.RegistrationUserDTO;
import ru.beamforce.entity.GridEntity;
import ru.beamforce.entity.UserEntity;
import ru.beamforce.repository.GridRepository;
import ru.beamforce.service.AdminUserService;
import ru.beamforce.service.RegistrationUserService;
import ru.beamforce.shortobject.NewUserInformer;
import test.Example;

import java.io.IOException;

/**
 * @author Andrey Korneychuk on 08-Apr-22
 * @version 1.0
 */
@Component
public class AfterStartComponent {

	private static final Logger LOGGER = LogManager.getLogger(AfterStartComponent.class);
	@Autowired
	private RegistrationUserService registrationUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private GridRepository gridRepository;
	@Autowired
	private PoCServerDriver poCServerDriver;

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
		if (gridRepository.existsById(2L)) {
			print("Example grid with offsets already exist");
		} else {
			// TODO saved entity can have ID != 1
			GridEntity gridEntity = new GridEntity();
			gridEntity.setId(1L);
			gridEntity.setGridContainer(Example.getExampleWithOffsets());
			gridEntity.setName("Example");
			gridEntity.setCommentary("Grid with offsets");
			gridRepository.save(gridEntity);
			print("Example grid with offsets created");
		}
	}

	@Order(20)
	@EventListener(ApplicationReadyEvent.class)
	public void checkPoCServer() {
		try {
			print("PoC Server status: " + poCServerDriver.get());
		} catch (IOException e) {
			print("PoC Server status: down");
		}
	}

	private void print(String string) {
		LOGGER.info(string);
	}
}