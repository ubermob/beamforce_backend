package ru.beamforce.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beamforce.dto.RegistrationUserDTO;
import ru.beamforce.service.RegistrationUserService;
import ru.beamforce.shortobject.NewUserInformer;

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

	public MainController(RegistrationUserService registrationUserService) {
		this.registrationUserService = registrationUserService;
	}

	@RequestMapping("/")
	public String showMainPage() {
		return "main";
	}

	@RequestMapping("/reg")
	public String showRegistrationPage(RegistrationUserDTO registrationUserDTO, Model model) {
		navBarDynamicUtil(model, "Регистрация");
		return "registration";
	}

	@RequestMapping("/reg/validation")
	public String showRegValidationPage(@Valid RegistrationUserDTO registrationUserDTO, Errors errors, Model model) {
		NewUserInformer informer = registrationUserService.getNewUserInformer(registrationUserDTO);
		navBarDynamicUtil(model, "Регистрация");
		if (errors.hasErrors() || informer.hasErrors()) {
			if (!informer.isAvailableName()) {
				errors.rejectValue("name", "error.registrationUserDTO"
						, "Такое имя пользователя уже занято");
			}
			if (!informer.isAvailableEmail()) {
				errors.rejectValue("email", "error.registrationUserDTO"
						, "Такой email уже занят");
			}
			return "registration";
		} else {
			// Success
			registrationUserService.createNewUser(registrationUserDTO);
			model.addAttribute("user_name", registrationUserDTO.getName());
			return "registration_success";
		}
	}

	@RequestMapping("/help")
	public String showHelpPage(Model model) {
		model.addAttribute("isNavBarHelp", true);
		return "help";
	}

	@RequestMapping("/about")
	public String showAboutPage(Model model) {
		model.addAttribute("isNavBarAbout", true);
		return "about";
	}

	@RequestMapping("/example")
	public String showSamplePage(Model model) {
		model.addAttribute("meta_example", new MetaKeyAndValue("meta.test.key", "abcd"));
		return "example";
	}

	private record MetaKeyAndValue(String key, String value) {
	}

	private void navBarDynamicUtil(Model model, String dynamicText) {
		model.addAttribute("isNavBarDynamic", true);
		model.addAttribute("navBarDynamicText", dynamicText);
	}
}