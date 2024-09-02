package cn.mimiknight.developer.kuca.spring.ecology;

import cn.mimiknight.developer.kuca.spring.ecology.exception.HandlerNotFoundException;
import cn.mimiknight.developer.kuca.spring.ecology.handler.EcologyRequestHandler;
import cn.mimiknight.developer.kuca.spring.ecology.handler.EcologyRequestHandlerBox;
import cn.mimiknight.developer.kuca.spring.ecology.model.request.EcologyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Objects;

/**
 * 请求处理器的执行器类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-02-28 21:23:05
 */
@Slf4j
@SuppressWarnings({"unchecked"})
public class EcologyRequestExecutor implements ApplicationContextAware, InitializingBean {

    private ApplicationContext appContext;
    private EcologyRequestHandlerBox erhBox;
    private EcologyParamValidationProcessor validationProcessor;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.erhBox = appContext.getBean(EcologyRequestHandlerBox.class);
        validationProcessor = appContext.getBean(EcologyParamValidationProcessor.class);
    }

    /**
     * 执行方法
     *
     * @param <Q>     请求参数泛型
     * @param <P>     响应参数泛型
     * @param <H>     处理器泛型
     * @param request 请求参数对象
     * @return {@link P} 响应
     */
    @SuppressWarnings({"unchecked"})
    public <Q extends EcologyRequest, P, H extends EcologyRequestHandler<Q, P>> P execute(Q request) {
        // 通过请求参数Class获取handler
        H handler = (H) erhBox.getRequestHandlerMap().get(request.getClass());
        return execute(request, handler);
    }

    /**
     * 执行方法
     *
     * @param <Q>     请求参数泛型
     * @param <P>     响应参数泛型
     * @param <H>     处理器泛型
     * @param request 请求参数对象
     * @param handler 处理器对象
     * @return {@link P} 响应
     */
    public <Q extends EcologyRequest, P, H extends EcologyRequestHandler<Q, P>> P execute(Q request, H handler) {
        if (Objects.isNull(handler)) {
            String requestName = request.getClass().getSimpleName();
            log.error("The handler is undefined or not managed by spring,request class name = {}", requestName);
            throw new HandlerNotFoundException("The handler is undefined or not managed by spring.");
        }
        // 执行请求参数校验
        validationProcessor.doRequestValidation(request);
        // 执行业务逻辑
        P response = doService(request, handler);
        // 执行响应参数校验
        validationProcessor.doResponseValidation(response);
        return response;
    }

    /**
     * 执行业务逻辑
     *
     * @param <Q>     请求参数泛型
     * @param <P>     响应参数泛型
     * @param <H>     处理器泛型
     * @param request 请求参数
     * @param handler 执行器
     * @return {@link P} 响应
     */
    private <Q extends EcologyRequest, P,
            H extends EcologyRequestHandler<Q, P>> P doService(Q request, H handler) {
        return handler.handle(request);
    }
}
