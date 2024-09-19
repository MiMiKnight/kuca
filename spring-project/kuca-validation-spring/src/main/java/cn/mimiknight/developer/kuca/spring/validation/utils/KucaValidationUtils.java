package cn.mimiknight.developer.kuca.spring.validation.utils;

import cn.mimiknight.developer.kuca.spring.validation.action.ConstraintHelper;
import cn.mimiknight.developer.kuca.spring.validation.annotation.KucaValidated;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    /**
     * 校验指定参数
     *
     * @param target                target
     * @param constraintAnnotations constraint annotations
     */
    public static void valid(Object target, List<Annotation> constraintAnnotations) {

        if (target instanceof Parameter) {
            Parameter parameter = Parameter.class.cast(target);
        }
        if (target instanceof Field) {
            Field field = Field.class.cast(target);
        }
    }

}
