package cn.mimiknight.developer.kuca.spring.validation.validator;

import cn.mimiknight.developer.kuca.spring.validation.annotation.validation.KucaSize;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 元素个数注解校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class SizeValidator implements ConstraintValidator<KucaSize, Object> {

    private int min;
    private int max;

    @Override
    public void initialize(KucaSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Object value) {
        if (null == value) {
            return true;
        }
        // 字符串
        if (value instanceof String) {
            String str = (String) value;
            return valid(str.length());
        }
        // 数组
        if (value.getClass().isArray()) {
            return valid(Array.getLength(value));
        }
        // 单列集合
        if (value instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) value;
            return valid(collection.size());
        }
        // 双列集合
        if (value instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) value;
            return valid(map.size());
        }
        // 默认放通
        return true;
    }

    private boolean valid(int size) {
        return (size >= min && size <= max);
    }
}
