package ru.beamforce.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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
public class LoggingGridAspect {

	private static final Logger LOGGER = LogManager.getLogger(LoggingGridAspect.class);

	@Before("execution(public String ru.beamforce.controller.html.UserController.newGridPost(..))")
	public void newGridPostAdvice(JoinPoint joinPoint) {
		GridInputDTO gridInputDTO = (GridInputDTO) joinPoint.getArgs()[0];
		String gridInfo = ", along: '%s', across: '%s'".formatted(gridInputDTO.getAlong(), gridInputDTO.getAcross());
		String message = "POST @Before [/user/model/new-grid/post]" + gridInfo
				.replace("\r", "")
				.replace("\n", "\\n")
				.replace("\t", "\\t");
		LOGGER.info(message);
	}

	@After("execution(public String ru.beamforce.controller.html.UserController.cloneGrid(..))")
	public void cloneGridAdvice(JoinPoint joinPoint) {
		Long gridId = (Long) joinPoint.getArgs()[0];
		String message = "Clone grid, id=" + gridId;
		LOGGER.info(message);
	}

	@After("execution(public String ru.beamforce.controller.html.UserController.deleteGrid(..))")
	public void deleteGridAdvice(JoinPoint joinPoint) {
		Long gridId = (Long) joinPoint.getArgs()[0];
		String message = "Delete grid, id=" + gridId;
		LOGGER.info(message);
	}
}