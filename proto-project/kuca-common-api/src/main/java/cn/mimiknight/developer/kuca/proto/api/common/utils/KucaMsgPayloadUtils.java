package cn.mimiknight.developer.kuca.proto.api.common.utils;

import cn.mimiknight.developer.kuca.proto.api.common.model.KucaMsgPayload;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 消息载荷工具类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 18:27:33
 */
public class KucaMsgPayloadUtils {

    private KucaMsgPayloadUtils() {
    }

    /**
     * 载入单个键值对消息
     * <p>
     * 支持指定载荷容器类型，如果传入的载荷容器类型为null默认使用{@link LinkedHashMap}作为载荷容器
     *
     * @param key     键名
     * @param value   值
     * @param payload 载荷容器
     * @return {@link KucaMsgPayload}<{@link K}, {@link Object}>
     */
    public static <K> KucaMsgPayload<K, Object> load(Map<K, Object> payload, K key, Object value) {
        if (null == payload) {
            payload = new LinkedHashMap<>();
        }
        return new KucaMsgPayload<>(payload).load(key, value);
    }

    /**
     * 载入单个键值对消息
     *
     * @param key   键名
     * @param value 值
     * @return {@link KucaMsgPayload}<{@link K}, {@link Object}>
     */
    public static <K> KucaMsgPayload<K, Object> load(K key, Object value) {
        return load(null, key, value);
    }

}
