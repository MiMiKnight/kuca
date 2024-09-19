package cn.mimiknight.developer.kuca.spring.validation.aspect;

import cn.mimiknight.developer.kuca.spring.validation.utils.KucaValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Slf4j
@Aspect
public class KucaValidationAspect implements Ordered {

    @Pointcut("@annotation(cn.mimiknight.developer.kuca.spring.validation.annotation.KucaValidated) " +
            "|| @within(cn.mimiknight.developer.kuca.spring.validation.annotation.KucaValidated)")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before(final JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return;
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getParameterCount() <= 0) {
            return;
        }
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            KucaValidationUtils.valid(parameters[i], args[i]);
        }
    }

    @Override
    public int getOrder() {
        return Byte.MAX_VALUE;
    }
}
