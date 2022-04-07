package ru.beamforce.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@Aspect
@Component
public class LoggingAspect {

	private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

	static {
		LOGGER.info("Start write");
	}

	@After("execution(public String ru.beamforce.controller.html.MainController.showMainPage())")
	public void mainPageAdvice() {
		LOGGER.info("GET [/]");
	}

	@After("execution(public String ru.beamforce.controller.html.UserController.showUserPage(..))")
	public void userAdvice(JoinPoint joinPoint) {
		var args = joinPoint.getArgs();
		for (var v : args) {
			if (v instanceof Principal) {
				LOGGER.info("GET [/user], user: '" + ((Principal) v).getName() + "'");
			}
		}
	}
}