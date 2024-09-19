package cn.mimiknight.developer.kuca.spring.validation.exception;

import cn.mimiknight.developer.kuca.spring.validation.action.ValidationDescriptor;

import java.lang.annotation.Annotation;

/**
 * kuca method argument not valid exception
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-20 00:28:00
 */
public class KucaMethodArgumentNotValidException extends RuntimeException {

    public <T, V, A extends Annotation> KucaMethodArgumentNotValidException(ValidationDescriptor<T, V, A> descriptor) {
        super();
    }
}
