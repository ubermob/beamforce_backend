package ru.beamforce.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.beamforce.service.ServerMessageService;
import ru.beamforce.shortobject.ShortUserInformation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.List;

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
	public String showUserPage(Model model, Principal principal) {
		ShortUserInformation sui = new ShortUserInformation(principal.getName(), "code-org");
		model.addAttribute("shortUserInformation", sui);
		if (serverMessageService.getMessage() != null) {
			model.addAttribute("server_message", serverMessageService.getMessage());
		}
		return "logged";
	}

	@RequestMapping("/model/new-model")
	public String uploadFileForm() {
		return "upload-new-model";
	}

	@PostMapping("/model/new-model/post")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			System.out.println("fileName: " + fileName);
			Path path = Path.of("J:\\Spring post file", fileName);
			file.transferTo(path);
			System.out.println("Transferred");
			List<String> strings = Files.readAllLines(path);
			System.out.println("Read");
			System.out.println(strings);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/user";
	}
}