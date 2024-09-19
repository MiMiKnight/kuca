package cn.mimiknight.developer.kuca.spring.validation.annotation.validation;

import cn.mimiknight.developer.kuca.spring.validation.annotation.KucaConstraint;
import cn.mimiknight.developer.kuca.spring.validation.validator.NotEmptyValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数非空校验注解
 *
 * <p>
 * 字符串的字符个数不为零 {@link String}
 * <p>
 * 数组的元素个数不为零
 * <p>
 * 单列集合的元素个数不为零 {@link java.util.Collection}
 * <p>
 * 双列集合的元素个数不为零 {@link java.util.Map}
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:05:34
 */
@KucaConstraint(validatedBy = {NotEmptyValidator.class})
@Documented
@Target(value = {ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Repeatable(value = KucaNotEmpty.List.class)
public @interface KucaNotEmpty {

    /**
     * 消息
     *
     * @return {@link String}
     */
    String message() default "{com.github.mimiknight.kuca.validation.annotation.validation.NotEmpty.message}";

    /**
     * 错误码
     *
     * @return {@link String}
     */
    String errorCode() default "";

    /**
     * 分组
     *
     * @return {@link String[] }
     */
    String[] groups() default {};

    /**
     * 注解校验的执行顺序
     *
     * @return int
     */
    int order() default -1;

    @Target(value = {ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        KucaNotEmpty[] value();
    }
}
