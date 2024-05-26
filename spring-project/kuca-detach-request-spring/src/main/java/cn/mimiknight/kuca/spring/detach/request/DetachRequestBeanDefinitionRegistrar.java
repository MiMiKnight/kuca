package cn.mimiknight.kuca.spring.detach.request;

import cn.mimiknight.kuca.proto.detach.DetachManager;
import cn.mimiknight.kuca.proto.detach.handler.DetachHandler;
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

public class DetachRequestBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {
        DetachManager manager = new DetachManager();
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ArrayList<Class<?>> classes = new ArrayList<>();

        try {
            Resource[] resources = patternResolver.getResources("classpath:**/*.class");
            for (Resource resource : resources) {
                MetadataReader reader = metadataReaderFactory.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                Class<?> loadedClass = classLoader.loadClass(className);
                if (DetachHandler.class.isAssignableFrom(loadedClass)) {
                    classes.add(loadedClass);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
