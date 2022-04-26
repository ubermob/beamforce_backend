package ru.beamforce.logger;

/**
 * @author Andrey Korneychuk on 23-Apr-22
 * @version 1.0
 */
public interface LoggingDb {

	void error(Exception exception);

	void error(Exception exception, String message);
}