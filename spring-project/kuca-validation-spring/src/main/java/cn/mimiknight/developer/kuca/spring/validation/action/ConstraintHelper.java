package cn.mimiknight.developer.kuca.spring.validation.action;

import cn.mimiknight.developer.kuca.spring.validation.annotation.KucaConstraint;
import cn.mimiknight.developer.kuca.spring.validation.exception.ValidationException;
import cn.mimiknight.developer.kuca.spring.validation.utils.KucaValidationUtils;
import cn.mimiknight.developer.kuca.spring.validation.validator.ConstraintValidator;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * constraint helper
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-19 15:13:43
 */
public class ConstraintHelper {

    private ConstraintHelper() {
    }

    public static final String MESSAGE = "message";
    public static final String ERROR_CODE = "errorCode";
    public static final String GROUPS = "groups";
    public static final String ORDER = "order";


    /**
     * 判断当前注解是否为校验注解
     *
     * @param annotation annotation
     * @return boolean
     */
    public static <A extends Annotation> boolean isConstraintAnnotation(A annotation) {
        Assert.notNull(annotation, "Parameter must not be null.");
        Class<? extends Annotation> type = annotation.annotationType();
        // 目标注解没有被指定注解修饰，则非校验注解
        if (KucaValidationUtils.isValidated(type)) {
            return false;
        }
        // 如果为校验注解，则Validator不允许为空
        KucaConstraint constraint = type.getDeclaredAnnotation(KucaConstraint.class);
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
        Object groups = attributes.get(GROUPS);
        Object order = attributes.get(ORDER);
        // 目标注解指定属性不存在，则非校验注解
        return null != errorCode && null != message && null != groups && null != order;
    }

    /**
     * 获取约束注解匹配的校验器
     *
     * @param annotation annotation
     * @return {@link List }<{@link ConstraintValidator }<{@link A }, {@link V }>>
     */
    public static <A extends Annotation, V> List<ConstraintValidator<A, V>> getValidators(A annotation) {
        Class<? extends Annotation> type = annotation.annotationType();
        KucaConstraint constraint = type.getDeclaredAnnotation(KucaConstraint.class);
        Class<? extends ConstraintValidator<?, ?>>[] validatedBy = constraint.validatedBy();

        return Arrays.stream(validatedBy).map(v -> {
            try {
                return (ConstraintValidator<A, V>) v.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ex) {
                String format = "%s create instance failed.";
                String tip = String.format(format, v.getName());
                throw new ValidationException(tip, ex);
            }
        }).collect(Collectors.toList());
    }

    /**
     * 获取目标对象的全部成员属性字段
     * <p>
     * 包括全部父类的继承属性
     *
     * @param target 目标对象
     * @return {@link List}<{@link Field}>
     */
    public static <V> List<Field> getAllFields(V target) {
        Assert.notNull(target, "Parameter must not be null.");
        Class<?> type = target.getClass();

        LinkedList<Field> fields = new LinkedList<>();

        while (null != type) {
            Field[] declaredFields = type.getDeclaredFields();
            List<Field> fieldList = Arrays.stream(declaredFields).collect(Collectors.toList());
            Collections.reverse(fieldList);
            fields.addAll(fieldList);
            type = type.getSuperclass();
        }
        return fields;
    }

    /**
     * 反射获取字段值
     *
     * @param target target
     * @param field  field
     * @return {@link Object }
     */
    public static <T> Object getFieldValue(T target, Field field) {
        boolean isAccessible = false;
        try {
            isAccessible = field.isAccessible();
            if (!isAccessible) {
                field.setAccessible(true);
            }
            return field.get(target);
        } catch (SecurityException | IllegalAccessException e) {
            throw new ValidationException("Get field value failed.", e);
        } finally {
            if (!isAccessible) {
                field.setAccessible(false);
            }
        }
    }


}
