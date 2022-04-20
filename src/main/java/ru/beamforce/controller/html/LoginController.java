package ru.beamforce.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Andrey Korneychuk on 17-Apr-22
 * @version 1.0
 */
@Controller
public class LoginController extends AbstractController {

	@GetMapping("/login")
	public String login(Model model) {
		navBarDynamicUtil(model, "Log in");
		return "login";
	}
}