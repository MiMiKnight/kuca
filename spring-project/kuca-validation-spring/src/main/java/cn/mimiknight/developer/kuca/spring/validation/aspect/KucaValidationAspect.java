package cn.mimiknight.developer.kuca.spring.validation.aspect;

import cn.mimiknight.developer.kuca.spring.validation.utils.KucaValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

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
        int parameterCount = method.getParameterCount();
        if (0 == parameterCount) {
            return;
        }
        for (Parameter parameter : method.getParameters()) {
            boolean isValidated = KucaValidationUtils.isValidated(parameter);
            if (!isValidated) {
                continue;
            }
            // 获取修饰参数的注解
            Annotation[] annotations = parameter.getDeclaredAnnotations();
            // 获取方法参数上的校验注解
            List<Annotation> constraintAnnotations = KucaValidationUtils.getConstraintAnnotations(annotations);
            if (CollectionUtils.isEmpty(constraintAnnotations)) {
                continue;
            }
            KucaValidationUtils.valid(parameter, constraintAnnotations);
        }
        log.info("ValidationAspect...before()...");
    }

    @Override
    public int getOrder() {
        return Byte.MAX_VALUE;
    }
}
