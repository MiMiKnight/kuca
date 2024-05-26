package cn.mimiknight.kuca.spring.detach.request;

import cn.mimiknight.kuca.proto.detach.DetachManager;
import cn.mimiknight.kuca.proto.detach.DetachManagerFactory;
import cn.mimiknight.kuca.proto.detach.executor.DetachHandleExecutor;
import cn.mimiknight.kuca.proto.detach.handler.DetachHandler;
import cn.mimiknight.kuca.spring.detach.request.executor.RequestDetachHandleExecutor;
import cn.mimiknight.kuca.spring.detach.request.handler.RequestDetachHandler;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetachRequestBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {
        // 实例化DetachManager
        DetachManagerFactory.create();
        String locationPattern = "classpath:**/*.class";
        List<Class<?>> classes = scanPackageGetClasses(locationPattern);
        List<Class<RequestDetachHandler>> handlerClasses = getScanClasses(classes, RequestDetachHandler.class);
        initHandlerMappings(handlerClasses);

        List<Class<RequestDetachHandleExecutor>> executorClasses = getScanClasses(classes, RequestDetachHandleExecutor.class);
        initExecutorMappings(executorClasses);
    }

    private void initHandlerMappings(List<Class<RequestDetachHandler>> classes) {
        if (CollectionUtils.isEmpty(classes)) {
            return;
        }
        DetachHandler handler = new DetachHandler() {
        };
        for (Class<RequestDetachHandler> clazz : classes) {
            DetachManagerFactory.getManager().getHandlerMappings().put(clazz, handler);
        }
    }

    private void initExecutorMappings(List<Class<RequestDetachHandleExecutor>> classes) {
        if (CollectionUtils.isEmpty(classes)) {
            return;
        }
        DetachHandleExecutor executor = new DetachHandleExecutor() {
        };
        for (Class<RequestDetachHandleExecutor> clazz : classes) {
            DetachManagerFactory.getManager().getExecutorMappings().put(clazz, executor);
        }
    }

    private List<Class<?>> scanPackageGetClasses(@NonNull String locationPattern) {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        List<Class<?>> classes = new ArrayList<>();

        try {
            Resource[] resources = patternResolver.getResources(locationPattern);
            if (ArrayUtils.isEmpty(resources)) {
                return classes;
            }
            for (Resource resource : resources) {
                MetadataReader reader = metadataReaderFactory.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                Class<?> loadedClass = classLoader.loadClass(className);
                classes.add(loadedClass);
            }
        } catch (IOException | ClassNotFoundException e) {
            return classes;
        }
        return classes;
    }

    private <T> List<Class<T>> getScanClasses(@NonNull List<Class<?>> classes, @NonNull Class<T> dataType) {
        ArrayList<Class<T>> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(classes)) {
            return list;
        }
        for (Class<?> clazz : classes) {
            if (dataType.isAssignableFrom(clazz)) {
                list.add((Class<T>) clazz);
            }
        }
        return list;
    }

}
