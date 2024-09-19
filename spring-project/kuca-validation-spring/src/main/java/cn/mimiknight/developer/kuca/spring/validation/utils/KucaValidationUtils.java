package cn.mimiknight.developer.kuca.spring.validation.utils;

import cn.mimiknight.developer.kuca.spring.validation.action.AnnotationDescriptor;
import cn.mimiknight.developer.kuca.spring.validation.annotation.KucaConstraint;
import cn.mimiknight.developer.kuca.spring.validation.annotation.KucaValidated;
import cn.mimiknight.developer.kuca.spring.validation.validator.ConstraintValidator;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static cn.mimiknight.developer.kuca.spring.validation.action.ConstraintHelper.ERROR_CODE;
import static cn.mimiknight.developer.kuca.spring.validation.action.ConstraintHelper.MESSAGE;

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

    public static List<Annotation> getConstraintAnnotations(Annotation[] annotations) {
        ArrayList<Annotation> list = new ArrayList<>();
        if (ArrayUtils.isEmpty(annotations)) {
            return list;
        }
        for (Annotation annotation : annotations) {
            if (isConstraintAnnotation(annotation)) {
                list.add(annotation);
            }
        }
        return list;
    }

    /**
     * 判断当前注解是否为校验注解
     *
     * @param annotation annotation
     * @return boolean
     */
    public static <A extends Annotation> boolean isConstraintAnnotation(A annotation) {
        Assert.notNull(annotation, "Parameter must not be null.");

        Class<?> clazz = annotation.getClass();
        // 目标注解不是注解类型，则非校验注解
        if (!clazz.isAnnotation()) {
            return false;
        }
        // 目标注解没有被指定注解修饰，则非校验注解
        if (!clazz.isAnnotationPresent(KucaConstraint.class)) {
            return false;
        }
        // 如果为校验注解，则Validator不允许为空
        KucaConstraint constraint = clazz.getDeclaredAnnotation(KucaConstraint.class);
        if (ArrayUtils.isEmpty(constraint.validatedBy())) {
            return false;
        }
        AnnotationDescriptor<Annotation> descriptor = new AnnotationDescriptor.Builder<>().setAnnotation(annotation).build();
        // 目标注解属性为空，则非校验注解
        Map<String, Object> attributes = descriptor.getAttributes();
        if (MapUtils.isEmpty(attributes)) {
            return false;
        }

        Object errorCode = attributes.get(ERROR_CODE);
        Object message = attributes.get(MESSAGE);
        // 目标注解指定属性不存在，则非校验注解
        return null != errorCode && null != message;
    }

    public static void valid(Object target) {

    }

}
