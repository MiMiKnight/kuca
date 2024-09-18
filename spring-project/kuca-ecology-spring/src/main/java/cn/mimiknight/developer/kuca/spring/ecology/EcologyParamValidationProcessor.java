package cn.mimiknight.developer.kuca.spring.ecology;

import cn.mimiknight.developer.kuca.spring.ecology.model.request.EcologyRequest;
import cn.mimiknight.developer.kuca.spring.ecology.model.response.EcologyResponse;
import cn.mimiknight.developer.kuca.spring.ecology.model.validator.RequestValidator;
import cn.mimiknight.developer.kuca.spring.ecology.model.validator.ResponseValidator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 参数校验后置处理器
 * <p>
 * BeanDefinitionRegistryPostProcessor 是在bean对象初始化前进行拦截
 * <p>
 * BeanFactoryPostProcessor  是在bean对象初始化后进行拦截
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-03-07 23:17:55
 */
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
public class EcologyParamValidationProcessor implements BeanFactoryPostProcessor {

    private interface Constant {

        /**
         * 初始化容量
         */
        int INIT_CAPACITY = 128;

        /**
         * handle方法名
         */
        String HANDLE_METHOD_NAME = "valid";

        /**
         * handle方法参数个数
         */
        int HANDLE_METHOD_PARAMETER_COUNT = 1;

    }

    /**
     * Request与Request Validation映射Map
     */
    @Getter
    private final ConcurrentMap<Class<EcologyRequest>, RequestValidator<?>> requestRequestValidationMap;

    /**
     * Request与Response Validation映射Map
     */
    @Getter
    private final ConcurrentMap<Class<EcologyRequest>, ResponseValidator<?>> requestResponseValidationMap;

    private EcologyProperties properties;

    public EcologyParamValidationProcessor() {
        requestRequestValidationMap = new ConcurrentHashMap<>(Constant.INIT_CAPACITY);
        requestResponseValidationMap = new ConcurrentHashMap<>(Constant.INIT_CAPACITY);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 获取请求参数校验对象
        Map<String, RequestValidator> requestValidations = beanFactory.getBeansOfType(RequestValidator.class);
        // 获取响应参数校验对象
        Map<String, ResponseValidator> responseValidations = beanFactory.getBeansOfType(ResponseValidator.class);
        // 属性
        properties = beanFactory.getBean(EcologyProperties.class);

        buildRequestValidationMap(requestValidations);
        buildResponseValidationMap(responseValidations);
    }

    /**
     * 构建 Request与Response Validation映射Map
     *
     * @param validators 校验集合
     */
    private void buildRequestValidationMap(Map<String, RequestValidator> validators) {
        if (MapUtils.isEmpty(validators)) {
            return;
        }
        for (RequestValidator validator : validators.values()) {
            for (Method method : AopUtils.getTargetClass(validator).getMethods()) {
                if (isRequestValidMethod(method)) {
                    Class<?> parameterType = method.getParameterTypes()[0];
                    requestRequestValidationMap.put((Class<EcologyRequest>) parameterType, validator);
                }
            }
        }
    }

    /**
     * 构建 Request与Request Validation映射Map
     *
     * @param validators 校验集合
     */
    private void buildResponseValidationMap(Map<String, ResponseValidator> validators) {
        if (MapUtils.isEmpty(validators)) {
            return;
        }
        for (ResponseValidator validator : validators.values()) {
            for (Method method : AopUtils.getTargetClass(validator).getMethods()) {
                if (isResponseValidMethod(method)) {
                    Class<?> parameterType = method.getParameterTypes()[0];
                    requestResponseValidationMap.put((Class<EcologyRequest>) parameterType, validator);
                }
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
     * 方法的第1个参数实现了paramType接口；
     *
     * @param method    方法
     * @param paramType 参数类型
     * @return boolean
     */
    private boolean isValidMethod(Method method, Class<?> paramType) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        return Modifier.isPublic(method.getModifiers())
                && !method.isSynthetic()
                && Constant.HANDLE_METHOD_NAME.equals(method.getName())
                && Constant.HANDLE_METHOD_PARAMETER_COUNT == method.getParameterCount()
                && paramType.isAssignableFrom(parameterTypes[0]);

    }

    /**
     * 是否为请求参数校验方法
     *
     * @param method 方法
     * @return boolean
     */
    private boolean isRequestValidMethod(Method method) {
        return isValidMethod(method, EcologyRequest.class);
    }

    /**
     * 是否为响应参数校验方法
     *
     * @param method 方法
     * @return boolean
     */
    private boolean isResponseValidMethod(Method method) {
        return isValidMethod(method, EcologyResponse.class);
    }

    /**
     * enable request validation
     *
     * @return boolean
     */
    private boolean enableRequestValidation() {
        if (Objects.isNull(properties)) {
            return false;
        }
        EcologyProperties.RequestValidation validation = properties.getRequestValidation();
        if (Objects.isNull(validation)) {
            return false;
        }
        return validation.isEnable();
    }

    /**
     * enable response validation
     *
     * @return boolean
     */
    private boolean enableResponseValidation() {
        if (Objects.isNull(properties)) {
            return false;
        }
        EcologyProperties.ResponseValidation validation = properties.getResponseValidation();
        if (Objects.isNull(validation)) {
            return false;
        }
        return validation.isEnable();
    }

    /**
     * 校验请求参数
     *
     * @param request 请求
     */
    public <Q> void doRequestValidation(Q request) {
        if (!enableRequestValidation()) {
            return;
        }
        if (Objects.isNull(request) || !(request instanceof EcologyRequest)) {
            return;
        }
        ConcurrentMap<Class<EcologyRequest>, RequestValidator<?>> validations = this.getRequestRequestValidationMap();
        if (MapUtils.isEmpty(validations)) {
            return;
        }
        RequestValidator<Q> validator = (RequestValidator<Q>) validations.get(request.getClass());
        if (null == validator) {
            return;
        }
        validator.valid(request);
    }

    /**
     * 校验响应参数
     *
     * @param response 请求
     */
    public <P> void doResponseValidation(P response) {
        if (!enableResponseValidation()) {
            return;
        }
        if (Objects.isNull(response) || !(response instanceof EcologyResponse)) {
            return;
        }
        ConcurrentMap<Class<EcologyRequest>, ResponseValidator<?>> validators = this.getRequestResponseValidationMap();
        if (MapUtils.isEmpty(validators)) {
            return;
        }
        ResponseValidator<P> validator = (ResponseValidator<P>) validators.get(response.getClass());
        if ((Objects.isNull(validator))) {
            return;
        }
        validator.valid(response);
    }

}
