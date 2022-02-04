package ru.beamforce.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beamforce.service.RegistrationUserService;
import ru.beamforce.shortobject.NewUserInformer;
import ru.beamforce.shortobject.RegistrationUser;

import javax.validation.Valid;

/**
 * @author Andrey Korneychuk on 31-Jan-22
 * @version 1.0
 */
@Controller
@RequestMapping
public class MainController {

	@Autowired
	private RegistrationUserService registrationUserService;

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
			// Validation errors
			return "registration";
		} else {
			NewUserInformer informer = registrationUserService.getNewUserInformer(registrationUser);
			if (informer.hasErrors()) {
				// DB errors
				if (!informer.isAvailableName()) {
					errors.rejectValue("name", "error.registrationUser"
							, "Такое имя пользователя уже занято");
				}
				if (!informer.isAvailableEmail()) {
					errors.rejectValue("email", "error.registrationUser"
							, "Такой email уже занят");
				}
				return "registration";
			}
			// Success
			registrationUserService.createNewUser(registrationUser);
			model.addAttribute("user_name", registrationUser.getName());
			return "registration_success";
		}
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