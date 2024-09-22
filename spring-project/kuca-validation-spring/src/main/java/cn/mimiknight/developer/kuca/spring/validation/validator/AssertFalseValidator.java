package cn.mimiknight.developer.kuca.spring.validation.validator;

import cn.mimiknight.developer.kuca.spring.validation.annotation.validation.KucaAssertFalse;

/**
 * 参数为false校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class AssertFalseValidator implements ConstraintValidator<KucaAssertFalse, Boolean> {

    @Override
    public boolean isValid(Boolean value) {
        if (null == value) {
            return true;
        }
        return Boolean.FALSE.equals(value);
    }
}
