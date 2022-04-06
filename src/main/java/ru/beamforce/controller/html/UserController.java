package ru.beamforce.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.beamforce.dto.EmailDTO;
import ru.beamforce.dto.TokenDTO;
import ru.beamforce.dto.UpdatePasswordDTO;
import ru.beamforce.entity.Organization;
import ru.beamforce.entity.User;
import ru.beamforce.service.OrganizationService;
import ru.beamforce.service.ServerMessageService;
import ru.beamforce.service.UserService;

import javax.validation.Valid;
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
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping
	public String showUserPage(Model model, Principal principal) {
		User user = userService.getUserByPrincipal(principal);
		System.out.println(user);
		try {
			System.out.println(user.getOrganization());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("user", user);
		if (serverMessageService.getMessage() != null) {
			model.addAttribute("server_message", serverMessageService.getMessage());
		}
		return "logged";
	}

	@RequestMapping("/settings")
	public String userSettings(EmailDTO emailDTO, Model model, Principal principal) {
		User user = userService.getUserByUsername(principal.getName());
		model.addAttribute("user", user);
		emailDTO.setEmail(user.getEmail());
		model.addAttribute("emailDTO", emailDTO);
		return "user_settings";
	}

	@RequestMapping("/settings/validation")
	public String userSettingsValidation(@Valid EmailDTO emailDTO, Errors errors, Model model, Principal principal) {
		User user = userService.getUserByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("emailDTO", emailDTO);
		if (errors.hasErrors()) {
			return "user_settings";
		} else {
			userService.updateEmail(user, emailDTO);
			return "redirect:/user/settings";
		}
	}

	@RequestMapping("/settings/update-password")
	public String updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		return "user_settings_update_password";
	}

	@RequestMapping("/settings/update-password/validation")
	public String updatePasswordValidation(@Valid UpdatePasswordDTO updatePasswordDTO, Errors errors, Principal principal) {
		User user = userService.getUserByPrincipal(principal);
		userService.comparePassword(user, updatePasswordDTO, errors);
		if (errors.hasErrors()) {
			return "user_settings_update_password";
		} else {
			userService.updatePassword(user, updatePasswordDTO);
			return "redirect:/user/settings";
		}
	}

	@RequestMapping("/settings/delete-email")
	public String deleteEmail(Principal principal) {
		User user = userService.getUserByUsername(principal.getName());
		userService.deleteEmail(user);
		return "redirect:/user/settings";
	}

	@RequestMapping("/settings/delete-user")
	public String deleteUser(Principal principal) {
		User user = userService.getUserByUsername(principal.getName());
		userService.deleteUser(user);
		return "redirect:/logout";
	}

	@RequestMapping("/settings/create-org")
	public String createOrganization(Organization organization) {
		return "user_settings_create_org";
	}

	@RequestMapping("/settings/create-org/validation")
	public String createOrganizationValidation(@Valid Organization organization, Errors errors, Principal principal) {
		boolean nameIsUnique = organizationService.nameIsUnique(organization);
		if (!nameIsUnique) {
			errors.rejectValue("name", "error.organization"
					, "Организация с таким именем недоступна");
		}
		if (errors.hasErrors()) {
			return "user_settings_create_org";
		} else {
			organizationService.createOrganization(userService.getUserByPrincipal(principal), organization);
			return "redirect:/user";
		}
	}

	@RequestMapping("/settings/join-org")
	public String joinOrganization(TokenDTO tokenDTO) {
		return "user_settings_join_org";
	}

	@RequestMapping("/settings/join-org/validation")
	public String joinOrganizationValidation(Principal principal, TokenDTO tokenDTO) {
		User user = userService.getUserByPrincipal(principal);
		userService.joinToOrganization(user, tokenDTO);
		return "redirect:/user";
	}

	@RequestMapping("/settings/leave-org")
	public String leaveOrganization(Principal principal) {
		userService.leaveOrganization(userService.getUserByPrincipal(principal));
		return "redirect:/user";
	}

	@RequestMapping("/settings/new-org-token")
	public String newOrganizationToken(Principal principal) {
		User user = userService.getUserByPrincipal(principal);
		organizationService.newOrganizationToken(user);
		return "redirect:/user/settings";
	}

	@RequestMapping("/model/new-model")
	public String uploadFileForm() {
		return "upload_new_model";
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

	@RequestMapping("/model/delete-list")
	public String deleteList() {
		return "user_model_delete_list";
	}
}