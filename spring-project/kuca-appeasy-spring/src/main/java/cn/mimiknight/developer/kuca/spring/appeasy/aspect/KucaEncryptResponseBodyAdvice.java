package cn.mimiknight.developer.kuca.spring.appeasy.aspect;

import cn.mimiknight.developer.kuca.proto.api.common.annotation.KucaEncryption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 加密响应体切面
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 10:57:09
 */
@Slf4j
@RestControllerAdvice
public class KucaEncryptResponseBodyAdvice implements ResponseBodyAdvice<Object>, Ordered, ApplicationContextAware, InitializingBean {

    private ApplicationContext appContext;

    KucaEncryption encryption;

    @Override
    public int getOrder() {
        return Byte.MAX_VALUE;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.hasMethodAnnotation(KucaEncryption.class)) {
            encryption = returnType.getMethodAnnotation(KucaEncryption.class);
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // TODO 待完善
        log.info("KucaEncryptResponseBodyAdvice...");
        return body;
    }

}
