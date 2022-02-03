package ru.beamforce.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Andrey Korneychuk on 31-Jan-22
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping
	public String showUserPage() {
		return "logged";
	}
}