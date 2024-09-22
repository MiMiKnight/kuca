package cn.mimiknight.developer.kuca.spring.validation.validator;

import cn.mimiknight.developer.kuca.spring.validation.annotation.validation.KucaLength;

/**
 * 字符串的字符个数校验注解
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class LengthValidator implements ConstraintValidator<KucaLength, String> {

    private int min;
    private int max;

    @Override
    public void initialize(KucaLength constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value) {
        if (null == value) {
            return true;
        }
        int size = value.length();
        return (size >= min && size <= max);
    }
}
