package cn.mimiknight.developer.kuca.spring.ecology.handler;

import cn.mimiknight.developer.kuca.spring.ecology.model.request.EcologyRequest;

/**
 * 请求处理器接口
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-31 14:37:32
 * @since v1.0
 */
public interface KucaEcologyRequestHandler<Q extends EcologyRequest, P> {

    /**
     * 处理方法
     *
     * @param request 请求参数
     * @return {@link P} 响应
     */
    P handle(Q request);

}
