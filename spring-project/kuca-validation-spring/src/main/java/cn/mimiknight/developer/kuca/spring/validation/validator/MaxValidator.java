package cn.mimiknight.developer.kuca.spring.validation.validator;


import cn.mimiknight.developer.kuca.spring.validation.annotation.validation.KucaMax;

/**
 * 最大值校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class MaxValidator implements ConstraintValidator<KucaMax, Number> {
    private double max;
    private double delta;

    @Override
    public void initialize(KucaMax constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.delta = constraintAnnotation.delta();
    }

    @Override
    public boolean isValid(Number value) {
        if (null == value) {
            return true;
        }
        double doubleValue = value.doubleValue();
        // value须小于等max
        return ((Double.compare(doubleValue, max) < 0) || (Math.abs(doubleValue - max) < delta));
    }
}
