package cn.mimiknight.developer.kuca.proto.detach.executor;

import cn.mimiknight.developer.kuca.proto.detach.handler.WithParamWithReturnDetachHandler;
import cn.mimiknight.developer.kuca.proto.detach.utils.DetachUtil;
import cn.mimiknight.developer.kuca.proto.detach.handler.WithoutParamWithReturnDetachHandler;
import lombok.NonNull;

/**
 * with return detach handle executor
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-30 19:26:09
 */
public class WithReturnDetachHandleExecutor implements DetachHandleExecutor {

    /**
     * execute
     *
     * @param handler handler
     * @param param   param
     * @return {@link P }
     */
    public <P, Q, H extends WithParamWithReturnDetachHandler<Q, P>> P execute(@NonNull H handler, Q param) {
        return handler.handle(param);
    }

    /**
     * execute
     *
     * @param handlerDataType handler data type
     * @param param           param
     * @return {@link P }
     */
    public <P, Q, H extends WithParamWithReturnDetachHandler<Q, P>> P execute(@NonNull Class<H> handlerDataType, Q param) {
        return DetachUtil.getHandler(handlerDataType).handle(param);
    }

    /**
     * execute
     *
     * @param handler handler
     * @return {@link P }
     */
    public <P, H extends WithoutParamWithReturnDetachHandler<P>> P execute(@NonNull H handler) {
        return handler.handle();
    }

    /**
     * execute
     *
     * @param handlerDataType handler data type
     * @return {@link P }
     */
    public <P, H extends WithoutParamWithReturnDetachHandler<P>> P execute(@NonNull Class<H> handlerDataType) {
        return DetachUtil.getHandler(handlerDataType).handle();
    }

}
