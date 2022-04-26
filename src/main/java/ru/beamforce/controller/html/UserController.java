package ru.beamforce.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.beamforce.dto.*;
import ru.beamforce.entity.GridEntity;
import ru.beamforce.entity.ModelEntity;
import ru.beamforce.entity.OrganizationEntity;
import ru.beamforce.entity.UserEntity;
import ru.beamforce.service.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @author Andrey Korneychuk on 31-Jan-22
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Autowired
	private ServerMessageService serverMessageService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private GridService gridService;
	@Autowired
	private ModelService modelService;
	@Autowired
	private PublicModelService publicModelService;

	@RequestMapping
	public String showUserPage(Model model, Principal principal) {
		UserEntity user = userService.getUserByPrincipal(principal);
		model.addAttribute("user", user);
		List<ModelEntity> organizationWideModelEntityList = modelService.getOrganizationWideModelEntityList(principal);
		model.addAttribute("modelList", organizationWideModelEntityList);
		model.addAttribute("userService", userService);
		if (serverMessageService.getMessage() != null) {
			model.addAttribute("server_message", serverMessageService.getMessage());
		}
		return "user";
	}

	@RequestMapping("/settings")
	public String userSettings(EmailDTO emailDTO, Model model, Principal principal) {
		UserEntity user = userService.getUserByUsername(principal.getName());
		model.addAttribute("user", user);
		emailDTO.setEmail(user.getEmail());
		model.addAttribute("emailDTO", emailDTO);
		model.addAttribute("isNavBarSettings", true);
		return "user_settings";
	}

	@RequestMapping("/settings/validation")
	public String userSettingsValidation(@Valid EmailDTO emailDTO, Errors errors, Model model, Principal principal) {
		UserEntity user = userService.getUserByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("emailDTO", emailDTO);
		model.addAttribute("isNavBarSettings", true);
		if (errors.hasErrors()) {
			return "user_settings";
		} else {
			userService.updateEmail(user, emailDTO);
			return "redirect:/user/settings";
		}
	}

	@RequestMapping("/settings/update-password")
	public String updatePassword(UpdatePasswordDTO updatePasswordDTO, Model model) {
		navBarDynamicUtil(model, "Изменить пароль");
		return "user_settings_update_password";
	}

	@RequestMapping("/settings/update-password/validation")
	public String updatePasswordValidation(@Valid UpdatePasswordDTO updatePasswordDTO, Errors errors
			, Principal principal, Model model) {
		UserEntity user = userService.getUserByPrincipal(principal);
		userService.comparePassword(user, updatePasswordDTO, errors);
		navBarDynamicUtil(model, "Изменить пароль");
		if (errors.hasErrors()) {
			return "user_settings_update_password";
		} else {
			userService.updatePassword(user, updatePasswordDTO);
			return "redirect:/user/settings";
		}
	}

	@RequestMapping("/settings/delete-email")
	public String deleteEmail(Principal principal) {
		UserEntity user = userService.getUserByUsername(principal.getName());
		userService.deleteEmail(user);
		return "redirect:/user/settings";
	}

	@RequestMapping("/settings/delete-user")
	public String deleteUser(Principal principal) {
		UserEntity user = userService.getUserByUsername(principal.getName());
		userService.deleteUser(user);
		return "redirect:/logout";
	}

	@RequestMapping("/settings/create-org")
	public String createOrganization(OrganizationEntity organization, Model model) {
		navBarDynamicUtil(model, "Создать организацию");
		return "user_settings_create_org";
	}

	@RequestMapping("/settings/create-org/validation")
	public String createOrganizationValidation(@Valid OrganizationEntity organization, Errors errors
			, Principal principal, Model model) {
		navBarDynamicUtil(model, "Создать организацию");
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
	public String joinOrganization(TokenDTO tokenDTO, Model model) {
		navBarDynamicUtil(model, "Присоединиться к организации");
		return "user_settings_join_org";
	}

	@RequestMapping("/settings/join-org/validation")
	public String joinOrganizationValidation(Principal principal, TokenDTO tokenDTO) {
		UserEntity user = userService.getUserByPrincipal(principal);
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
		UserEntity user = userService.getUserByPrincipal(principal);
		organizationService.newOrganizationToken(user);
		return "redirect:/user/settings";
	}

	@RequestMapping("/model/new-model")
	public String newModel(Principal principal, Model model, ModelInputDTO modelInputDTO) {
		List<GridEntity> gridList = gridService.getGridList(principal);
		model.addAttribute("gridList", gridList);
		navBarDynamicUtil(model, "Новая модель");
		model.addAttribute("modelInputDTO", modelInputDTO);
		return "upload_new_model";
	}

	@PostMapping("/model/new-model/post")
	public String newModelPost(
			@RequestParam("geometry-file") MultipartFile geometryFile
			, @RequestParam("reinforcement-file") MultipartFile reinforcementFile
			, Model model
			, ModelInputDTO modelInputDTO
			, Principal principal) {
		modelService.add(geometryFile, reinforcementFile, modelInputDTO, principal);
		return "redirect:/user";
	}

	@RequestMapping("/model/new-grid")
	public String newGrid(GridInputDTO gridInputDTO, Model model) {
		navBarDynamicUtil(model, "Новая сетка");
		return "upload_new_grid";
	}

	@PostMapping("/model/new-grid/post")
	public String newGridPost(GridInputDTO gridInputDTO, Principal principal) {
		gridService.add(gridInputDTO, principal);
		return "redirect:/user";
	}

	@RequestMapping("/model/operations")
	public String modelOperations(Principal principal, Model model) {
		List<ModelEntity> personalModelEntityList = modelService.getPersonalModelEntityList(principal);
		model.addAttribute("modelList", personalModelEntityList);
		List<GridEntity> gridList = gridService.getGridList(principal);
		model.addAttribute("gridList", gridList);
		navBarDynamicUtil(model, "Операции");
		return "user_model_operations";
	}

	@PostMapping("/model/operations/access-level")
	public String setAccessLevel(@RequestParam(name = "id") long id, @RequestParam(name = "level") byte level
			, Principal principal) {
		// todo
		if (level == 3) {
			publicModelService.makePublic(id, principal);
		}
		return "redirect:/user/model/operations";
	}

	@PostMapping("/model/operations/clone-model")
	public String cloneModel(@RequestParam(name = "id") long id, Principal principal) {
		modelService.clone(principal, id);
		return "redirect:/user/model/operations";
	}

	@PostMapping("/model/operations/delete-model")
	public String deleteModel(@RequestParam(name = "id") long id, Principal principal) {
		modelService.delete(principal, id);
		return "redirect:/user/model/operations";
	}

	@PostMapping("/model/operations/clone-grid")
	public String cloneGrid(@RequestParam(name = "id") long id, Principal principal) {
		gridService.clone(principal, id);
		return "redirect:/user/model/operations";
	}

	@PostMapping("/model/operations/delete-grid")
	public String deleteGrid(@RequestParam(name = "id") long id, Principal principal) {
		gridService.delete(principal, id);
		return "redirect:/user/model/operations";
	}
}