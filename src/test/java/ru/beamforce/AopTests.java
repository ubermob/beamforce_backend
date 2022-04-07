package ru.beamforce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.beamforce.controller.html.MainController;
import ru.beamforce.controller.html.UserController;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@SpringBootTest
public class AopTests {

	@Autowired
	MainController mainController;
	@Autowired
	UserController userController;

	@Test
	void test1() {
		mainController.showMainPage();
	}

/*	@Test
	void test2() {
		userController.showUserPage();
	}*/
}