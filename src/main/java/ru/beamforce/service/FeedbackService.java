package ru.beamforce.service;

import ru.beamforce.entity.FeedbackEntity;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 27-Apr-22
 * @version 1.0
 */
public interface FeedbackService {

	void add(FeedbackEntity feedbackEntity, Principal principal);
}