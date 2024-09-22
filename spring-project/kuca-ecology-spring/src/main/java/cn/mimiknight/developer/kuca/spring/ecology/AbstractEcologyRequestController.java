package cn.mimiknight.developer.kuca.spring.ecology;

import cn.mimiknight.developer.kuca.spring.ecology.handler.KucaEcologyRequestHandlerBox;
import cn.mimiknight.developer.kuca.spring.ecology.model.request.EcologyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * 请求处理控制器抽象类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-31 16:32:45
 * @since v1.0
 */
@Slf4j
public abstract class AbstractEcologyRequestController implements ApplicationContextAware, InitializingBean {
    private ApplicationContext appContext;
    private KucaEcologyRequestExecutor executor;
    private KucaEcologyRequestHandlerBox box;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.executor = appContext.getBean(KucaEcologyRequestExecutor.class);
        this.box = appContext.getBean(KucaEcologyRequestHandlerBox.class);
    }

    /**
     * 处理方法
     *
     * @param <Q>              接口入参泛型
     * @param <P>              接口出参泛型
     * @param request          接口入参
     * @param responseDataType 响应参数数据类型
     * @return response 响应参数
     */
    protected <Q extends EcologyRequest, P> P handle(Q request, Class<P> responseDataType) {
        Assert.notNull(request, "The request argument is required; it must not be null");
        // 获取实际响应类型
        Class<?> actualResponseDataType = box.getRequestResponseMap().get(request.getClass());
        // 判断实际的响应类型和传入的响应类型是否一致
        boolean isAssignableFrom = responseDataType.isAssignableFrom(actualResponseDataType);
        // 类型不一致则报错
        if (!isAssignableFrom) {
            log.error("The responseDataType is invalid,InputDataType = {},ActualDataType = {}", responseDataType, actualResponseDataType);
            throw new IllegalArgumentException("The responseDataType is invalid");
        }
        // 响应结果
        Object response = executor.execute(request);
        return responseDataType.cast(response);
    }

}
