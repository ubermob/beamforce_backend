package ru.beamforce.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String showRegistrationPage(Model model) {
		System.out.println(decorate("/reg"));
		model.addAttribute("user", new RegistrationUser());
		return "registration";
	}

	@RequestMapping("/reg/validation")
	public String showRegValidationPage(@Valid RegistrationUser registrationUser, BindingResult errors, Model model) {
		System.out.println(decorate("/reg/validation"));
		System.out.println("RAW: " + registrationUser);
		if (errors.hasErrors()) {
			System.out.println(errors.getFieldError("name"));
			System.out.println(errors.getFieldError("email"));
			System.out.println(errors.getFieldError("password"));
			return "registration";
		}
		model.addAttribute("user", registrationUser);
		return "redirect:/reg/success";
	}

	@RequestMapping("/reg/success")
	public String successRegistrationPage(Model model) {
		System.out.println(decorate("/reg/success"));
		RegistrationUser registrationUser = (RegistrationUser) model.getAttribute("user");
		System.out.println("RAW: " + registrationUser);
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