package ru.beamforce.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beamforce.entity.FeedbackEntity;
import ru.beamforce.service.FeedbackService;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 25-Apr-22
 * @version 1.0
 */
@Controller
public class FeedbackController extends AbstractController {

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping("/feedback")
	public String feedback(FeedbackEntity feedbackEntity, Model model) {
		navBarDynamicUtil(model, "Отзыв");
		return "feedback";
	}

	@RequestMapping("/feedback/push")
	public String feedbackPush(FeedbackEntity feedbackEntity, Principal principal) {
		feedbackService.add(feedbackEntity, principal);
		return "redirect:/";
	}
}