package cn.mimiknight.developer.kuca.spring.validation.action;


import cn.mimiknight.developer.kuca.spring.validation.validator.ConstraintValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * validation descriptor
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-19 15:13:22
 */
public class ValidationDescriptor<T, V, A extends Annotation> {

    private final T target;
    private final Field field;
    private final ConstraintAnnotationDescriptor<A> annotationDescriptor;
    private final List<ConstraintValidator<A, V>> validators;

    private ValidationDescriptor(T target, Field field, A annotation) {
        this.target = target;
        this.field = field;

        ConstraintAnnotationDescriptor.Builder<A> builder = new ConstraintAnnotationDescriptor.Builder<>();
        this.annotationDescriptor = builder.setAnnotation(annotation).build();

        this.validators = ConstraintHelper.getValidators(annotation);
    }

    public static <V, T, A extends Annotation> ValidationDescriptor<T, V, A> create(T target, Class<V> valueClass, Field field, A annotation) {
        return new ValidationDescriptor<>(target, field, annotation);
    }

    public T getTarget() {
        return target;
    }

    public Field getField() {
        return field;
    }

    public ConstraintAnnotationDescriptor<A> getAnnotationDescriptor() {
        return annotationDescriptor;
    }

    public List<ConstraintValidator<A, V>> getValidators() {
        return validators;
    }

}
