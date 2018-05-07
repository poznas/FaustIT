package faust.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Before("Pointcuts.logging()")
    public void before(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        logger.info("===> @Before: calling method: " + method);
        for (Object arg : joinPoint.getArgs()) {
            logger.info(">arg: " + arg);
        }

    }

    @AfterReturning(
            pointcut = "Pointcuts.logging()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        String method = joinPoint.getSignature().toShortString();
        logger.info("<---- @After: from method: " + method);

        logger.info("\t\t\tresult: " + result);
    }
}
