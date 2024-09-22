package cn.mimiknight.developer.kuca.proto.api.common.model;

import cn.mimiknight.developer.kuca.proto.api.common.utils.KucaMsgPayloadUtils;

import java.util.Map;

/**
 * 消息载荷
 * <p>
 * 推荐结合消息载荷工具类使用，工具类{@link KucaMsgPayloadUtils}
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-03-08 19:23:18
 */
public class KucaMsgPayload<K, V> {

    private final Map<K, V> payload;

    public KucaMsgPayload(Map<K, V> payload) {
        this.payload = payload;
    }

    /**
     * 载入单个键值对消息
     *
     * @param key   键名
     * @param value 值
     * @return {@link KucaMsgPayload}<{@link K}, {@link V}>
     */
    public KucaMsgPayload<K, V> load(K key, V value) {
        payload.put(key, value);
        return this;
    }

    /**
     * 批量载入键值对消息
     *
     * @param map 数据
     * @return {@link KucaMsgPayload}<{@link K}, {@link V}>
     */
    public KucaMsgPayload<K, V> loadAll(Map<? extends K, ? extends V> map) {
        payload.putAll(map);
        return this;
    }

    /**
     * 如果键名不存在，载入单个键值对消息
     *
     * @param key   键名
     * @param value 值
     * @return {@link KucaMsgPayload}<{@link K}, {@link V}>
     */
    public KucaMsgPayload<K, V> loadIfAbsent(K key, V value) {
        payload.putIfAbsent(key, value);
        return this;
    }

    /**
     * 从载荷容器中根据键名删除内容
     *
     * @param key 键名
     * @return {@link KucaMsgPayload}<{@link K}, {@link V}>
     */
    public KucaMsgPayload<K, V> remove(K key) {
        payload.remove(key);
        return this;
    }

    /**
     * 从载荷容器中根据键名和值删除内容
     *
     * @param key   键名
     * @param value 值
     * @return {@link KucaMsgPayload}<{@link K}, {@link V}>
     */
    public KucaMsgPayload<K, V> remove(K key, V value) {
        payload.remove(key, value);
        return this;
    }

    /**
     * 清空载荷容器
     *
     * @param key 键名
     * @return {@link KucaMsgPayload}<{@link K}, {@link V}>
     */
    public KucaMsgPayload<K, V> clear(K key) {
        payload.clear();
        return this;
    }

    /**
     * 消息载入结束，返回消息载荷内容
     *
     * @return {@link Map}<{@link K}, {@link V}>
     */
    public Map<K, V> finished() {
        return this.payload;
    }

}
