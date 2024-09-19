package cn.mimiknight.developer.kuca.spring.validation.utils;

import cn.mimiknight.developer.kuca.spring.validation.action.ConstraintHelper;
import cn.mimiknight.developer.kuca.spring.validation.annotation.KucaConstraint;
import cn.mimiknight.developer.kuca.spring.validation.annotation.KucaValidated;
import cn.mimiknight.developer.kuca.spring.validation.exception.ValidationException;
import cn.mimiknight.developer.kuca.spring.validation.validator.ConstraintValidator;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class KucaValidationUtils {

    private KucaValidationUtils() {
    }


    /**
     * 指定对象是否被{@link KucaValidated}注解修饰
     *
     * @param target target
     * @return boolean
     */
    public static boolean isValidated(Object target) {
        if (Objects.isNull(target)) {
            return false;
        }
        KucaValidated validated = target.getClass().getAnnotation(KucaValidated.class);
        return !Objects.isNull(validated);
    }

    /**
     * 是否为基本数据类型或者值为null
     *
     * @param target target
     * @return boolean
     */
    public static boolean isPrimitiveOrNull(Object target) {
        if (Objects.isNull(target)) {
            return true;
        }
        return target.getClass().isPrimitive();
    }

    /**
     * 获取约束注解
     *
     * @param annotations annotations
     * @return {@link List }<{@link Annotation }>
     */
    public static List<Annotation> getConstraintAnnotations(Annotation[] annotations) {
        ArrayList<Annotation> list = new ArrayList<>();
        if (ArrayUtils.isEmpty(annotations)) {
            return list;
        }
        for (Annotation annotation : annotations) {
            if (ConstraintHelper.isConstraintAnnotation(annotation)) {
                list.add(annotation);
            }
        }
        return list;
    }


}
