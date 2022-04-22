package ru.beamforce.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Andrey Korneychuk on 22-Apr-22
 * @version 1.0
 */
@Aspect
@Component
public class LoggingModelAspect {

	private static final Logger LOGGER = LogManager.getLogger(LoggingModelAspect.class);

	@Before("execution(public String ru.beamforce.controller.html.UserController.newModelPost(..))")
	public void foo() {
		String message = "POST @Before [/user/model/new-model/post]";
		LOGGER.info(message);
	}
}