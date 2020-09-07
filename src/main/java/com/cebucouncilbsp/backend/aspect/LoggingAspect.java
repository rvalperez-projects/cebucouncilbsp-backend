package com.cebucouncilbsp.backend.aspect;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	/**
	 * logBeforeController
	 *
	 * @param point Object
	 * @return Object
	 * @throws Throwable Exception
	 */
	@Before("execution(* com.cebucouncilbsp.backend.controller.*.*(..)))")
	public void logBeforeController(final JoinPoint point) {
		Signature signature = point.getSignature();
		List<Object> args = new ArrayList<>();
		for (Object arg : point.getArgs()) {
			args.add(arg.toString());
		}
		LOGGER.info("Starting {} with arguments {}.", signature.getName(), args);
	}

	/**
	 * logAfterController
	 *
	 * @param point Object
	 * @return Object
	 * @throws Throwable Exception
	 */
	@AfterReturning("execution(* com.cebucouncilbsp.backend.controller.*.*(..)))")
	public void logAfterController(final JoinPoint point) {
		Signature signature = point.getSignature();
		LOGGER.info("Method {} ends successfully.", signature.getName());
	}

}
