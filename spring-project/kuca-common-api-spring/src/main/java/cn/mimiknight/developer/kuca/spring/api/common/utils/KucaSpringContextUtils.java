package cn.mimiknight.developer.kuca.spring.api.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Kuca Spring上下文工具类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-03-08 22:12:05
 */
public final class KucaSpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        KucaSpringContextUtils.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return KucaSpringContextUtils.context;
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return context.getBean(name, requiredType);
    }

    public static Object getBean(String name, Object... args) {
        return context.getBean(name, args);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    public static <T> T getBean(Class<T> requiredType, Object... args) {
        return context.getBean(requiredType, args);
    }

}
