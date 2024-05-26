package cn.mimiknight.kuca.spring.detach.request;

import cn.mimiknight.kuca.proto.detach.DetachManager;
import cn.mimiknight.kuca.proto.detach.DetachManagerFactory;
import cn.mimiknight.kuca.proto.detach.handler.DetachHandler;
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
        DetachManager manager = DetachManagerFactory.create();
        String locationPattern = "classpath:**/*.class";
        List<Class<RequestDetachHandler>> handlerClasses = scanPackageGetClasses(locationPattern, RequestDetachHandler.class);
        if (CollectionUtils.isEmpty(handlerClasses)) {
            return;
        }
        DetachHandler handler = new DetachHandler() {
        };
        for (Class<RequestDetachHandler> clazz : handlerClasses) {
            manager.getHandlerMappings().put(clazz, handler);
        }
    }

    private <T> List<Class<T>> scanPackageGetClasses(@NonNull String locationPattern, @NonNull Class<T> dataType) {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        List<Class<T>> classes = new ArrayList<>();

        try {
            Resource[] resources = patternResolver.getResources(locationPattern);
            if (ArrayUtils.isEmpty(resources)) {
                return classes;
            }
            for (Resource resource : resources) {
                MetadataReader reader = metadataReaderFactory.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                Class<?> loadedClass = classLoader.loadClass(className);
                if (dataType.isAssignableFrom(loadedClass)) {
                    classes.add((Class<T>) loadedClass);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return classes;
        }
        return classes;
    }
}
