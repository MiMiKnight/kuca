package cn.mimiknight.kuca.spring.detach.request.annotation;

import cn.mimiknight.kuca.spring.detach.request.DetachRequestBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({DetachRequestBeanDefinitionRegistrar.class})
public @interface EnableRequestDetach {
}
