package cn.mimiknight.developer.kuca.proto.api.common.annotation;

import cn.mimiknight.developer.kuca.proto.api.common.constant.KucaAlgorithm;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 解密标识注解
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 10:59:47
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface KucaDecryption {

    /**
     * algorithm
     *
     * @return {@link KucaAlgorithm }
     */
    KucaAlgorithm algorithm() default KucaAlgorithm.NONE;
}

