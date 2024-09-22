package cn.mimiknight.developer.kuca.spring.appeasy.aspect;

import cn.mimiknight.developer.kuca.proto.api.common.annotation.KucaDecryption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;


/**
 * 解密请求体切面 Decrypt
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 10:57:09
 */
@Slf4j
@RestControllerAdvice
public class KucaDecryptRequestBodyAdvice implements RequestBodyAdvice, Ordered {

    KucaDecryption decryption;

    @Override
    public int getOrder() {
        return Byte.MAX_VALUE;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (methodParameter.hasMethodAnnotation(KucaDecryption.class)) {
            decryption = methodParameter.getMethodAnnotation(KucaDecryption.class);
            return true;
        }
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
