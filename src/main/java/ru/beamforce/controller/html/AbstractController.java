package ru.beamforce.controller.html;

import org.springframework.ui.Model;

/**
 * @author Andrey Korneychuk on 19-Apr-22
 * @version 1.0
 */
public abstract class AbstractController {

	void navBarDynamicUtil(Model model, String dynamicText) {
		model.addAttribute("isNavBarDynamic", true);
		model.addAttribute("navBarDynamicText", dynamicText);
	}
}