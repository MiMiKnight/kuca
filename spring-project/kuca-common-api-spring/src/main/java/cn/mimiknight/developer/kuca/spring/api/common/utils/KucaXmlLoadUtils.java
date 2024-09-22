package cn.mimiknight.developer.kuca.spring.api.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * kuca xml load utils
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-22 08:07:57
 */
public final class KucaXmlLoadUtils {

    private static final Logger logger = LoggerFactory.getLogger(KucaXmlLoadUtils.class);

    private KucaXmlLoadUtils() {
    }

    public static Properties load(String location) {
        return KucaResourceLoadUtils.loadXML(location);
    }

    public static Object getValue(Properties properties, Object key) {
        return properties.get(key);
    }

    public static String getValue(Properties properties, String key) {
        return properties.getProperty(key);
    }

    public static String getValue(Properties properties, String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getValueInt(Properties properties, String key) {
        String value = properties.getProperty(key);
        return Integer.parseInt(value);
    }

    public static int getValueInt(Properties properties, String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (null == value) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    public static long getValueLong(Properties properties, String key) {
        String value = properties.getProperty(key);
        return Long.parseLong(value);
    }

    public static long getValueLong(Properties properties, String key, long defaultValue) {
        String value = properties.getProperty(key);
        if (null == value) {
            return defaultValue;
        }
        return Long.parseLong(value);
    }

    public static float getValueFloat(Properties properties, String key) {
        String value = properties.getProperty(key);
        return Float.parseFloat(value);
    }

    public static float getValueFloat(Properties properties, String key, float defaultValue) {
        String value = properties.getProperty(key);
        if (null == value) {
            return defaultValue;
        }
        return Float.parseFloat(value);
    }

    public static double getValueDouble(Properties properties, String key) {
        String value = properties.getProperty(key);
        return Double.parseDouble(value);
    }

    public static double getValueDouble(Properties properties, String key, double defaultValue) {
        String value = properties.getProperty(key);
        if (null == value) {
            return defaultValue;
        }
        return Double.parseDouble(value);
    }

    public static boolean getValueBoolean(Properties properties, String key) {
        String value = properties.getProperty(key);
        return Boolean.parseBoolean(value);
    }

    public static boolean getValueBoolean(Properties properties, String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (null == value) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

}
