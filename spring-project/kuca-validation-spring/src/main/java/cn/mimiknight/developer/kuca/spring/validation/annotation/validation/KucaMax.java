package cn.mimiknight.developer.kuca.spring.validation.annotation.validation;

import cn.mimiknight.developer.kuca.spring.validation.annotation.KucaConstraint;
import cn.mimiknight.developer.kuca.spring.validation.validator.MaxValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 最大值校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:05:34
 */
@KucaConstraint(validatedBy = {MaxValidator.class})
@Documented
@Target(value = {ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(value = RetentionPolicy.RUNTIME)
@Repeatable(value = KucaMax.List.class)
public @interface KucaMax {

    /**
     * 最大值
     *
     * @return int
     */
    double max() default Integer.MAX_VALUE;

    /**
     * 精确度
     * <p>
     * 默认：10负6次方
     *
     * @return double
     */
    double delta() default 1E-6;

    /**
     * 消息
     *
     * @return {@link String}
     */
    String message() default "{com.github.mimiknight.kuca.validation.annotation.validation.Max.message}";

    /**
     * 错误码
     *
     * @return {@link String}
     */
    String errorCode() default "";

    /**
     * 分组
     *
     * @return {@link Class}<{@link ?}>{@link []}
     */
    Class<?>[] groups() default {};

    @Target(value = {ElementType.FIELD, ElementType.LOCAL_VARIABLE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        KucaMax[] value();
    }
}
