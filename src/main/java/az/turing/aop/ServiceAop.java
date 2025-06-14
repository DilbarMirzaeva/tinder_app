package az.turing.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class ServiceAop {

    @Pointcut(value = "execution(* az.turing.service.impl.*.*(..))")
    public void allServiceImplMethods() {
    }

    @Before(value = "allServiceImplMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Method start successfully: {}", joinPoint.getSignature().getName());
    }

    @After(value = "allServiceImplMethods()")
    public void logAfterMethod(JoinPoint joinPoint) {
        log.info("Method finished successfully: {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "allServiceImplMethods()", returning = "result")
    public void logAfterReturningMethod(JoinPoint joinPoint, Object result) {
        log.info("Method {} | with result {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(value = "allServiceImplMethods()", throwing = "ex")
    public void logAfterThrowingMethod(JoinPoint joinPoint, Exception ex) {
        log.error("Method {} | with exception {}", joinPoint.getSignature().getName(), ex.getLocalizedMessage());
    }
}
