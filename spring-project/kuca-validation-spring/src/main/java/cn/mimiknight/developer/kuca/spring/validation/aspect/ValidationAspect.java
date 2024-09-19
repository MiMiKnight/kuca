package cn.mimiknight.developer.kuca.spring.validation.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
@Slf4j
@Aspect
public class ValidationAspect {

    @Pointcut("@annotation(cn.mimiknight.developer.kuca.spring.validation.annotation.KucaValidated)")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before(final JoinPoint joinPoint) {
        log.info("ValidationAspect...before()...");
    }
}
