package ru.beamforce.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beamforce.service.ServerMessageService;

/**
 * @author Andrey Korneychuk on 31-Jan-22
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ServerMessageService serverMessageService;

	@RequestMapping
	public String showUserPage(Model model) {
		if (serverMessageService.getMessage() != null) {
			model.addAttribute("server_message", serverMessageService.getMessage());
		}
		return "logged";
	}
}