package cn.mimiknight.developer.kuca.spring.validation.validator;

import java.lang.annotation.Annotation;

/**
 * 约束验证器接口
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:10:52
 */
public interface ConstraintValidator<A extends Annotation, V> {

    /**
     * 初始化
     *
     * @param constraintAnnotation 约束注释
     */
    default void initialize(A constraintAnnotation) {
    }

    /**
     * 是否检验通过
     *
     * @param value 被校验值
     * @return boolean
     */
    boolean isValid(V value);
}
