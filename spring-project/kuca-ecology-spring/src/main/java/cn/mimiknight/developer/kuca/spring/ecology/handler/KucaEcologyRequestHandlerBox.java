package cn.mimiknight.developer.kuca.spring.ecology.handler;

import cn.mimiknight.developer.kuca.spring.ecology.exception.RequestRepeatBindException;
import cn.mimiknight.developer.kuca.spring.ecology.model.request.EcologyRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 请求处理器的锁装载容器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-03-15 19:16:53
 */
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
public class KucaEcologyRequestHandlerBox implements ApplicationContextAware, InitializingBean {

    private interface Constant {

        /**
         * 初始化容量
         */
        int INIT_CAPACITY = 128;

        /**
         * handle方法名
         */
        String HANDLE_METHOD_NAME = "handle";

        /**
         * handle方法参数个数
         */
        int HANDLE_METHOD_PARAMETER_COUNT = 1;

    }

    private ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    /**
     * Request与Handler映射Map
     */
    @Getter
    private final ConcurrentMap<Class<EcologyRequest>, KucaEcologyRequestHandler> requestHandlerMap;

    /**
     * Request与Response映射Map
     */
    @Getter
    private final ConcurrentMap<Class<EcologyRequest>, Class<?>> requestResponseMap;


    public KucaEcologyRequestHandlerBox() {
        requestHandlerMap = new ConcurrentHashMap<>(Constant.INIT_CAPACITY);
        requestResponseMap = new ConcurrentHashMap<>(Constant.INIT_CAPACITY);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    /**
     * 初始化方法
     */
    public void init() {
        initRequestHandlerMap();
    }

    /**
     * 初始化Map
     */
    private void initRequestHandlerMap() {
        Map<String, KucaEcologyRequestHandler> handlerMap = appContext.getBeansOfType(KucaEcologyRequestHandler.class);
        if (MapUtils.isEmpty(handlerMap)) {
            return;
        }
        for (KucaEcologyRequestHandler handler : handlerMap.values()) {
            for (Method method : AopUtils.getTargetClass(handler).getMethods()) {
                buildRequestHandlerResponseMap(handler, method);
            }
        }
    }

    /**
     * 当前方法是否为Handler的默认handle方法
     * <p>
     * public修改的方法；
     * 方法为非人工合成的；
     * 方法名为handle；
     * 方法参数个数为1；
     * 方法的第1个参数实现了EcologyRequest接口；
     *
     * @param method 方法
     * @return boolean
     */
    private boolean isHandleMethod(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        return Modifier.isPublic(method.getModifiers())
                && !method.isSynthetic()
                && Constant.HANDLE_METHOD_NAME.equals(method.getName())
                && Constant.HANDLE_METHOD_PARAMETER_COUNT == method.getParameterCount()
                && EcologyRequest.class.isAssignableFrom(parameterTypes[0]);

    }

    /**
     * 当前方法不是Handler的默认handle方法
     *
     * @param method 方法
     * @return boolean
     */
    private boolean isNotHandleMethod(Method method) {
        return !isHandleMethod(method);
    }

    /**
     * 初始化requestHandlerMap、handlerResponseMap
     * <p>
     * 绑定关系：
     * <p>
     * Handler:Request=1:1 and Request:Handler=1:1
     * <p>
     * Handler:(Request,Response)=1:1 and (Request,Response):Handler=1:1
     *
     * @param handler Handler对象
     * @param method  handle方法
     */
    private <H extends KucaEcologyRequestHandler<?, ?>> void buildRequestHandlerResponseMap(H handler, Method method) {
        if (isNotHandleMethod(method)) {
            return;
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<EcologyRequest> requestClass = (Class<EcologyRequest>) parameterTypes[0];
        Class<?> responseClass = method.getReturnType();

        // Handler:Request=1:1 and Request:Handler=1:1
        requestHandlerMap.compute(requestClass, (k, v) -> {
            if (null != v) {
                String format = "The request can not repeat bind handler,request = %s,handler = %s";
                String tip = String.format(format, k.getSimpleName(), v.getClass().getSimpleName());
                log.error(tip);
                throw new RequestRepeatBindException(tip);
            }
            return handler;
        });
        // Request:Response=1:1 and Response:Request=1:N
        requestResponseMap.put(requestClass, responseClass);
    }

}
