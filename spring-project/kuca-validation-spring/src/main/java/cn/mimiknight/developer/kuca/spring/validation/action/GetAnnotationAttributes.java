package cn.mimiknight.developer.kuca.spring.validation.action;

import cn.mimiknight.developer.kuca.spring.validation.exception.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * get annotation attributes
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-19 15:13:34
 */
public final class GetAnnotationAttributes implements PrivilegedAction<Map<String, Object>> {

    private final Annotation annotation;

    public static GetAnnotationAttributes action(Annotation annotation) {
        return new GetAnnotationAttributes(annotation);
    }

    private GetAnnotationAttributes(Annotation annotation) {
        this.annotation = annotation;
    }

    /**
     * 获取注解属性Map集合
     */
    @Override
    public Map<String, Object> run() {
        Method[] methods = annotation.annotationType().getDeclaredMethods();
        Map<String, Object> attributes = new HashMap<>(methods.length);

        for (Method method : methods) {
            // Exclude synthetic methods
            if (method.isSynthetic()) {
                continue;
            }
            makeAccessible(method);
            String attributeName = method.getName();
            try {
                attributes.put(method.getName(), method.invoke(annotation));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                String format = "Unable to get attribute '%2$s' from annotation %1$s.";
                String message = String.format(format, annotation.getClass(), attributeName);
                throw new ValidationException(message, e);
            } finally {
                makeNotAccessible(method);
            }
        }
        return toImmutableMap(attributes);
    }

    private <K, V> Map<K, V> toImmutableMap(Map<K, V> map) {
        switch (map.size()) {
            case 0:
                return Collections.emptyMap();
            case 1:
                Map.Entry<K, V> entry = map.entrySet().iterator().next();
                return Collections.singletonMap(entry.getKey(), entry.getValue());
            default:
                return Collections.unmodifiableMap(map);
        }
    }

    private static void makeAccessible(Method method) {
        if ((!Modifier.isPublic(method.getModifiers()) ||
                !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
            method.setAccessible(true);
        }
    }

    private static void makeNotAccessible(Method method) {
        if (method.isAccessible()) {
            method.setAccessible(false);
        }
    }

}
