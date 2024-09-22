package cn.mimiknight.developer.kuca.proto.detach.executor;

import cn.mimiknight.developer.kuca.proto.detach.handler.WithParamWithoutReturnDetachHandler;
import cn.mimiknight.developer.kuca.proto.detach.handler.WithoutParamWithoutReturnDetachHandler;
import cn.mimiknight.developer.kuca.proto.detach.utils.DetachUtil;
import lombok.NonNull;

/**
 * without return detach handle executor
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-30 19:26:05
 */
public class WithoutReturnDetachHandleExecutor implements DetachHandleExecutor {

    /**
     * execute
     *
     * @param handler handler
     * @param param   param
     */
    public <Q, H extends WithParamWithoutReturnDetachHandler<Q>> void execute(@NonNull H handler, Q param) {
        handler.handle(param);
    }

    /**
     * execute
     *
     * @param handlerDataType handler data type
     * @param param           param
     */
    public <Q, H extends WithParamWithoutReturnDetachHandler<Q>> void execute(@NonNull Class<H> handlerDataType, Q param) {
        DetachUtil.getHandler(handlerDataType).handle(param);
    }

    /**
     * execute
     *
     * @param handler handler
     */
    public <H extends WithoutParamWithoutReturnDetachHandler> void execute(@NonNull H handler) {
        handler.handle();
    }

    /**
     * execute
     *
     * @param handlerDataType handler data type
     */
    public <H extends WithoutParamWithoutReturnDetachHandler> void execute(@NonNull Class<H> handlerDataType) {
        DetachUtil.getHandler(handlerDataType).handle();
    }

}
