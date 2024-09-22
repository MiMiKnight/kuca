package cn.mimiknight.developer.kuca.spring.appeasy.aspect;

import cn.mimiknight.developer.kuca.spring.api.common.utils.KucaSpringContextUtils;
import cn.mimiknight.developer.kuca.spring.appeasy.KucaAppEasyProperties;
import cn.mimiknight.developer.kuca.spring.appeasy.model.response.KucaServiceResponse;
import cn.mimiknight.developer.kuca.spring.appeasy.utils.KucaAppEasyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 统一响应体数据结构切面
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 10:57:09
 */
@Slf4j
@RestControllerAdvice
public class KucaUnifyResponseBodyStructureAdvice implements ResponseBodyAdvice<Object>, Ordered {

    @Override
    public int getOrder() {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        return config.getUnifyResponseBody().getOrder();
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 响应类型非指定的JSON媒体类型
        if (!(MediaType.APPLICATION_JSON.equals(selectedContentType))) {
            return body;
        }
        Class<?> parameterType = returnType.getParameterType();
        // 异常响应或者人为构造ServiceResponse时
        if (KucaServiceResponse.class.isAssignableFrom(parameterType)) {
            KucaServiceResponse kucaServiceResponse = (KucaServiceResponse) body;
            setStatusCode(response, kucaServiceResponse.getStatus());
            return body;
        }
        // 正常响应
        setStatusCode(response, HttpStatus.OK.value());
        return KucaAppEasyUtils.buildOkServiceResponse(body);
    }

    private void setStatusCode(ServerHttpResponse response, int statusCode) {
        response.setStatusCode(HttpStatusCode.valueOf(statusCode));
    }
}
