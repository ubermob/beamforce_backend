package ru.beamforce.service;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utoolsdriver.minididriver.MinidiDriverWithCache;

import java.io.IOException;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
@Service
public class ServerMessageServiceImpl implements ServerMessageService {

	// TODO replace redis
	@Autowired
	private MinidiDriverWithCache driver;

	@Override
	public String getMessage() {
		try {
			return getMessagePrivate();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getMessagePrivate() throws IOException, ParseException {
		return driver.getIfExist("message");
	}
}