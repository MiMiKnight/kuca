package cn.mimiknight.developer.kuca.spring.validation.validator;

import cn.mimiknight.developer.kuca.spring.validation.annotation.validation.KucaNotNull;

/**
 * 参数非空校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class NotNullValidator implements ConstraintValidator<KucaNotNull, Object> {
    @Override
    public void initialize(KucaNotNull constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value) {
        return value != null;
    }
}
