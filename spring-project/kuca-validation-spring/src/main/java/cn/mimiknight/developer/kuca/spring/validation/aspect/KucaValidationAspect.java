package cn.mimiknight.developer.kuca.spring.validation.aspect;

import cn.mimiknight.developer.kuca.spring.validation.utils.KucaValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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

    /**
     * 环绕通知
     *
     * @param point point
     * @return {@link Object }
     * @throws Throwable throwable
     */
    @Around(value = "pointcut()")
    public Object doAround(final ProceedingJoinPoint point) throws Throwable {
        validParams(point);
        Object proceed = point.proceed();
        validReturn(point, proceed);
        return proceed;
    }

    /**
     * 校验入参
     */
    private void validParams(final ProceedingJoinPoint point) {
        Object[] args = point.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return;
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if (method.getParameterCount() <= 0) {
            return;
        }
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Class<?> type = parameter.getType();
            KucaValidationUtils.valid(parameter, type.cast(args[i]));
        }
    }

    /**
     * 校验返回值
     */
    private void validReturn(final ProceedingJoinPoint point, Object value) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> returnType = method.getReturnType();
        KucaValidationUtils.valid(value, returnType);
    }

    @Override
    public int getOrder() {
        return Byte.MAX_VALUE;
    }
}
