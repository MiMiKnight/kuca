package cn.mimiknight.developer.kuca.spring.validation.validator;

import cn.mimiknight.developer.kuca.spring.validation.annotation.validation.KucaNull;

/**
 * 参数为空校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class NullValidator implements ConstraintValidator<KucaNull, Object> {
    @Override
    public void initialize(KucaNull constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value) {
        return value == null;
    }
}
