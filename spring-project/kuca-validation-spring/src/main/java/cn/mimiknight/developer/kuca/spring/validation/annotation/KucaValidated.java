package cn.mimiknight.developer.kuca.spring.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Kuca校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-08 01:06:11
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
public @interface KucaValidated {

    String[] groups() default {};
}
