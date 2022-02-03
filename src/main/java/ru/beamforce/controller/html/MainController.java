package ru.beamforce.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beamforce.entity.RegistrationUser;

import javax.validation.Valid;

/**
 * @author Andrey Korneychuk on 31-Jan-22
 * @version 1.0
 */
@Controller
@RequestMapping
public class MainController {

	@RequestMapping("/")
	public String showMainPage() {
		return "main";
	}

	@RequestMapping("/reg")
	public String showRegistrationPage(RegistrationUser registrationUser) {
		return "registration";
	}

	@RequestMapping("/reg/validation")
	public String showRegValidationPage(@Valid RegistrationUser registrationUser, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "registration";
		}
		model.addAttribute("user_name", registrationUser.getName());
		return "registration_success";
	}

	@RequestMapping("/help")
	public String showHelpPage() {
		return "help";
	}

	@RequestMapping("/about")
	public String showAboutPage() {
		return "about";
	}

	private String decorate(String string) {
		return "--- " + string + " ---";
	}
}