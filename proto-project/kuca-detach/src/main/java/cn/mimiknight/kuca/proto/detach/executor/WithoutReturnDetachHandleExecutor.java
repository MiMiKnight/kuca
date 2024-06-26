package cn.mimiknight.kuca.proto.detach.executor;

import cn.mimiknight.kuca.proto.detach.handler.WithParamWithoutReturnDetachHandler;
import cn.mimiknight.kuca.proto.detach.handler.WithoutParamWithoutReturnDetachHandler;
import cn.mimiknight.kuca.proto.detach.utils.DetachUtil;
import lombok.NonNull;

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
