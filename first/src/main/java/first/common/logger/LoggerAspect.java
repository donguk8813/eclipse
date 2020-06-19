package first.common.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggerAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

	static String name = "";
	static String type = "";
	
	@Around("execution(* first..controller.*Controller.*(..)) or execution(* first..service.*Impl.*(..)) or execution(* first..dao.*DAO.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint ) throws Throwable{
		type = joinPoint.getSignature().getDeclaringTypeName();
		
		if(type.indexOf("Controller") > -1) {
			name = "Controller \t: ";
		}else if(type.indexOf("Service") > -1) {
			name = "ServiceImple \t: ";
		}else if(type.indexOf("DAO") > -1 ) {
			name = "DAO \t\t: ";
		}
		logger.debug(name + type +"."+joinPoint.getSignature().getName()+"()");
		return joinPoint.proceed();
		
	}
}
