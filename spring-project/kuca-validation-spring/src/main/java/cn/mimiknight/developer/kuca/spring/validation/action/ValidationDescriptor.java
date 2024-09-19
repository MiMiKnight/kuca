package cn.mimiknight.developer.kuca.spring.validation.action;


import cn.mimiknight.developer.kuca.spring.validation.exception.KucaMethodArgumentNotValidException;
import cn.mimiknight.developer.kuca.spring.validation.validator.ConstraintValidator;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * validation descriptor
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-19 15:13:22
 */
@Getter
public class ValidationDescriptor<T, V, A extends Annotation> {

    private final T target;
    private final V value;
    private final ConstraintAnnotationDescriptor<A> annotationDescriptor;
    private final List<ConstraintValidator<A, V>> validators;

    private ValidationDescriptor(T target, V value, A annotation) {
        this.target = target;
        this.value = value;

        ConstraintAnnotationDescriptor.Builder<A> builder = new ConstraintAnnotationDescriptor.Builder<>();
        this.annotationDescriptor = builder.setAnnotation(annotation).build();
        this.validators = ConstraintHelper.getValidators(annotation);
    }

    /**
     * create
     *
     * @param target     target
     * @param value      value
     * @param annotation annotation
     * @return {@link ValidationDescriptor }<{@link T }, {@link V }, {@link A }>
     */
    public static <V, T, A extends Annotation> ValidationDescriptor<T, V, A> create(T target, V value, A annotation) {
        return new ValidationDescriptor<>(target, value, annotation);
    }

    /**
     * 校验
     */
    public void valid() {
        if (CollectionUtils.isEmpty(validators)) {
            return;
        }
        for (ConstraintValidator<A, V> validator : validators) {
            validator.initialize(annotationDescriptor.getAnnotation());
            if (!validator.isValid(value)) {
                throw new KucaMethodArgumentNotValidException(this);
            }
        }
    }

}
