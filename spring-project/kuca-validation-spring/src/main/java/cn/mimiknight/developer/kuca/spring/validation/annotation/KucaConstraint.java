package cn.mimiknight.developer.kuca.spring.validation.annotation;

import cn.mimiknight.developer.kuca.spring.validation.validator.ConstraintValidator;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 约束
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:12:00
 */
@Documented
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface KucaConstraint {

    Class<? extends ConstraintValidator<? extends Annotation, ?>>[] validatedBy();
}

