package ru.beamforce.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import utools.exceptiontools.ExceptionToString;

/**
 * @author Andrey Korneychuk on 23-Apr-22
 * @version 1.0
 */
@Component
public class LoggingDbImpl implements LoggingDb {

	private static final Logger LOGGER = LogManager.getLogger(LoggingDbImpl.class);

	@Override
	public void error(Exception exception) {
		LOGGER.error(exception);
		LOGGER.error(ExceptionToString.transform(exception));
	}

	@Override
	public void error(Exception exception, String message) {
		LOGGER.error(message);
		LOGGER.error(exception);
		LOGGER.error(ExceptionToString.transform(exception));
	}
}