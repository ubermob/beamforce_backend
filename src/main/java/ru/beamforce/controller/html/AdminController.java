package ru.beamforce.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Andrey Korneychuk on 02-Feb-22
 * @version 1.0
 */
@Controller
public class AdminController {

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
}