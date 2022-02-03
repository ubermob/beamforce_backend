package ru.beamforce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.beamforce.entity.Student;

import javax.validation.Valid;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
@Controller
public class TestController {
	@GetMapping("/test")
	public String getForm(Student student) {
		return "test";
	}

	@PostMapping("/test/save-student")
	public String submitStudentDetails(@Valid Student student, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "test";
		} else {
			model.addAttribute("successMsg", "Details saved successfully!!");
			return "test-save";
		}
	}
}