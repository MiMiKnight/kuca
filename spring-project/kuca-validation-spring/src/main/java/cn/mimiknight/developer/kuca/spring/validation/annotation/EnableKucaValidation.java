package cn.mimiknight.developer.kuca.spring.validation.annotation;

import cn.mimiknight.developer.kuca.spring.validation.aspect.ValidationAspect;
import org.springframework.context.annotation.Import;

@Import(value = {ValidationAspect.class})
public interface EnableKucaValidation {
}
