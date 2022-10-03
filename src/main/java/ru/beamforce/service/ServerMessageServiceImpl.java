package ru.beamforce.service;

import org.springframework.stereotype.Service;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
@Service
public class ServerMessageServiceImpl implements ServerMessageService {

	@Override
	public String getMessage() {
		return "Server working in demo mode";
	}
}