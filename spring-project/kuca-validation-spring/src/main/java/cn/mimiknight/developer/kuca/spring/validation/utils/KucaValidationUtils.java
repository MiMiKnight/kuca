package cn.mimiknight.developer.kuca.spring.validation.utils;

import cn.mimiknight.developer.kuca.spring.validation.action.ConstraintHelper;
import cn.mimiknight.developer.kuca.spring.validation.action.ValidationDescriptor;
import cn.mimiknight.developer.kuca.spring.validation.annotation.KucaValidated;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * kuca validation utils
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-19 23:43:50
 */
public final class KucaValidationUtils {

    private KucaValidationUtils() {
    }

    /**
     * 指定对象是否被{@link KucaValidated}注解修饰
     *
     * @param target target
     * @return boolean
     */
    public static boolean isValidated(Parameter target) {
        if (Objects.isNull(target)) {
            return false;
        }
        return target.isAnnotationPresent(KucaValidated.class);
    }

    /**
     * 指定对象是否被{@link KucaValidated}注解修饰
     *
     * @param target target
     * @return boolean
     */
    public static boolean isValidated(Field target) {
        if (Objects.isNull(target)) {
            return false;
        }
        return target.isAnnotationPresent(KucaValidated.class);
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
     * 校验方法参数
     *
     * @param target 目标参数
     * @param value  参数值
     */
    public static void valid(Parameter target, Object value) {
        if (Objects.isNull(target)) {
            return;
        }
        if (!KucaValidationUtils.isValidated(target)) {
            return;
        }
        // 参数数据类型
        Class<?> type = target.getType();
        // 获取修饰参数的注解
        Annotation[] annotations = target.getDeclaredAnnotations();
        // 获取方法参数上的校验注解
        List<Annotation> constraintAnnotations = KucaValidationUtils.getConstraintAnnotations(annotations);
        // 表层校验
        if (CollectionUtils.isNotEmpty(constraintAnnotations)) {
            valid(target, value, constraintAnnotations);
            return;
        }
        // 参数为基本数据类型或者值为null则不执行嵌套校验  反之执行嵌套校验
        if (!Objects.isNull(value) && !type.isPrimitive()) {
            // TODO 嵌套校验
        }
    }

    /**
     * 方法参数表层校验
     *
     * @param target                target
     * @param value                 value
     * @param constraintAnnotations constraint annotations
     */
    private static void valid(Parameter target, Object value, List<Annotation> constraintAnnotations) {
        if (CollectionUtils.isEmpty(constraintAnnotations)) {
            return;
        }
        for (Annotation constraint : constraintAnnotations) {
            ValidationDescriptor.create(target, value, constraint).valid();
        }
    }

}
