package cn.mimiknight.developer.kuca.spring.api.common.utils;

import cn.mimiknight.developer.kuca.spring.api.common.exception.KucaResourceLoadException;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * kuca resource loader utils
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-22 08:06:43
 */
public final class KucaResourceLoadUtils implements ResourceLoaderAware {

    private static ResourceLoader loader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        loader = resourceLoader;
    }

    public static ResourceLoader getResourceLoader() {
        return KucaResourceLoadUtils.loader;
    }

    /**
     * 获取资源
     *
     * @param location 资源文件位置
     * @return {@link Resource}
     */
    public static Resource getResource(String location) {
        return loader.getResource(location);
    }

    /**
     * 正则加载资源文件
     * <p>
     * 示例：
     * <p>
     * case01：classpath表示仅加载当前项目类路径下符合条件的文件
     * <p>
     * location = "classpath:/application-*.yaml"
     * <p>
     * case02：classpath* 不仅加载当前项目类路径下的文件，还包括所有jar包下符合条件的文件
     * <p>
     * location = "classpath*:/application-*.yaml"
     *
     * @param locationPattern 文件位置正则表达式
     * @return 资源文件数组
     */
    public static Resource[] patternLoad(String locationPattern) {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            return resolver.getResources(locationPattern);
        } catch (IOException e) {
            throw new KucaResourceLoadException("Load resource failed,file location pattern = %s", e, locationPattern);
        }
    }

    /**
     * 加载资源文件
     * <p>
     * 示例：
     * <pre>
     *   Properties properties = ResourceLoaderUtils.load("classpath:conf/api-path.properties", StandardCharsets.UTF_8);
     * </pre>
     *
     * @param location 资源文件位置
     * @return {@link Properties}
     */
    public static File load(String location) {
        try {
            Resource resource = getResource(location);
            return resource.getFile();
        } catch (IOException e) {
            throw new KucaResourceLoadException("Fail to load file,file location = %s", e, location);
        }
    }

    /**
     * 加载资源文件
     * <p>
     * 示例：
     * <pre>
     *   Properties properties = ResourceLoaderUtils.load("classpath:conf/api-path.properties", StandardCharsets.UTF_8);
     * </pre>
     *
     * @param location 资源文件位置
     * @param charset  字符集
     * @return {@link Properties}
     */
    public static Properties load(String location, Charset charset) {
        Resource resource = getResource(location);
        Properties properties = new Properties();
        try (
                InputStream is = resource.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, charset);
                BufferedReader bf = new BufferedReader(isr);
        ) {
            properties.load(bf);
            return properties;
        } catch (IOException e) {
            throw new KucaResourceLoadException("Fail to load property file,file location = %s", e, location);
        }
    }

    /**
     * 加载XML资源文件
     * <p>
     * 示例：
     * <pre>
     *   Properties properties = ResourceLoaderUtils.load("classpath:conf/api-path.xml");
     * </pre>
     *
     * @param location XML文件位置
     * @return {@link Properties}
     */
    public static Properties loadXML(String location) {
        Resource resource = getResource(location);
        Properties properties = new Properties();
        try (
                InputStream is = resource.getInputStream();
        ) {
            properties.loadFromXML(is);
            return properties;
        } catch (IOException e) {
            throw new KucaResourceLoadException("Fail to load property file,file location = %s", e, location);
        }
    }

    public ClassLoader getClassLoader() {
        return loader.getClassLoader();
    }
}
