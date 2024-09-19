package cn.mimiknight.developer.kuca.spring.validation.annotation;

import cn.mimiknight.developer.kuca.spring.validation.aspect.KucaValidationAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Kuca校验开关启用注解
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-19 17:33:37
 */
@Import(value = {KucaValidationAspect.class})
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableKucaValidation {
}
