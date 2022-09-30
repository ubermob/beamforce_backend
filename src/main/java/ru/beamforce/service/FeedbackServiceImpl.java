package ru.beamforce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beamforce.dao.FeedbackDao;
import ru.beamforce.entity.FeedbackEntity;
import ru.beamforce.entity.UserEntity;
import ru.beamforce.repository.FeedbackRepository;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * @author Andrey Korneychuk on 27-Apr-22
 * @version 1.0
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private UserService userService;
	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public void add(FeedbackEntity feedbackEntity, Principal principal) {
		UserEntity user = userService.getUserByPrincipal(principal);
		if (feedbackDao.getFeedbackListSize(user.getId()) < Limit.USER_FEEDBACK_LIMIT) {
			feedbackEntity.setLocalDateTime(LocalDateTime.now());
			feedbackEntity.setAuthorId(userService.getUserByPrincipal(principal).getId());
			feedbackRepository.save(feedbackEntity);
		}
	}
}