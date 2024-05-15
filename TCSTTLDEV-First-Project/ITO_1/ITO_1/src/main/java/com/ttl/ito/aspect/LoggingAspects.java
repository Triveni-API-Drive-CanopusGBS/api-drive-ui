package com.ttl.ito.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspects {

	private Logger logger = Logger.getLogger(Logger.class);

	@Before("execution(* com.ttl.ito.*.*.*.*(..))")
	public void logBeforeAllMethods(JoinPoint joinPoint) {

		logger.info(" *** Inside  : " + joinPoint.getSignature() + " START *** ");
	}

	@AfterReturning(pointcut = "execution(* com.ttl.ito.*.*.*.*(..))", returning = "retVal")
	public void afterReturningFromAllMethods(JoinPoint jp, Object retVal) {
		logger.info(" Returning : " + retVal.toString());
		logger.info(" *** Inside  : " + jp.getSignature() + " END ***");
	}

}
