package ru.beamforce.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.beamforce.dto.GridInputDTO;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
@Aspect
@Component
public class LoggingGridPostAspect {

	private static final Logger LOGGER = LogManager.getLogger(LoggingGridPostAspect.class);

	@Before("execution(public String ru.beamforce.controller.html.UserController.newGridPost(..))")
	public void mainPageAdvice(JoinPoint joinPoint) {
		GridInputDTO gridInputDTO = (GridInputDTO) joinPoint.getArgs()[0];
		String info = ", along: '%s', across: '%s'".formatted(gridInputDTO.getAlong(), gridInputDTO.getAcross());
		LOGGER.info("POST @Before [/user/model/new-grid/post]" + info);
	}
}