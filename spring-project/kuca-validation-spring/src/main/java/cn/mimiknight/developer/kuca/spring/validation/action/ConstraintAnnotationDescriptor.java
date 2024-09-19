package cn.mimiknight.developer.kuca.spring.validation.action;

import cn.mimiknight.developer.kuca.spring.validation.exception.ValidationException;

import java.lang.annotation.Annotation;

/**
 * constraint annotation descriptor
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-19 15:13:50
 */
public final class ConstraintAnnotationDescriptor<A extends Annotation> extends AnnotationDescriptor<A> {

    private ConstraintAnnotationDescriptor(A annotation) {
        super(annotation);
    }

    private ConstraintAnnotationDescriptor(AnnotationDescriptor<A> descriptor) {
        super(descriptor);
    }

    public String getErrorCode() {
        return getMandatoryAttribute(ConstraintHelper.ERROR_CODE, String.class);
    }

    public String getMessage() {
        return getMandatoryAttribute(ConstraintHelper.MESSAGE, String.class);
    }

    public String[] getGroups() {
        return getMandatoryAttribute(ConstraintHelper.GROUPS, String[].class);
    }

    public int getOrder() {
        return getMandatoryAttribute(ConstraintHelper.ORDER, Integer.class);
    }

    public static class Builder<A extends Annotation> extends AnnotationDescriptor.Builder<A> {

        @Override
        public Builder<A> setAnnotation(A annotation) {
            if (!ConstraintHelper.isConstraintAnnotation(annotation)) {
                throw new ValidationException("The parameter annotation is not constraint annotation type.");
            }
            super.setAnnotation(annotation);
            return this;
        }

        @Override
        public ConstraintAnnotationDescriptor<A> build() {
            return new ConstraintAnnotationDescriptor<>(super.build());
        }

    }

}
