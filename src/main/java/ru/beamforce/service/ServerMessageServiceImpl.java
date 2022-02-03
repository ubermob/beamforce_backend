package ru.beamforce.service;

import org.springframework.stereotype.Service;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
@Service
public class ServerMessageServiceImpl implements ServerMessageService {

	// TODO replace redis
	private String tmpMessage = "Важное сообщение";

	@Override
	public String getMessage() {
		return tmpMessage;
	}
}