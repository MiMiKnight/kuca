package cn.mimiknight.kuca.proto.detach.executor;

import cn.mimiknight.kuca.proto.detach.handler.WithParamWithReturnDetachHandler;
import cn.mimiknight.kuca.proto.detach.handler.WithoutParamWithReturnDetachHandler;
import cn.mimiknight.kuca.proto.detach.utils.DetachUtil;
import lombok.NonNull;

public class WithReturnDetachHandleExecutor implements DetachHandleExecutor {

    public <P, Q, H extends WithParamWithReturnDetachHandler<Q, P>> P execute(@NonNull H handler, Q param) {
        return handler.handle(param);
    }

    public <P, Q, H extends WithParamWithReturnDetachHandler<Q, P>> P execute(@NonNull Class<H> handlerDataType, Q param) {
        return DetachUtil.getHandler(handlerDataType).handle(param);
    }

    public <P, H extends WithoutParamWithReturnDetachHandler<P>> P execute(@NonNull H handler) {
        return handler.handle();
    }

    public <P, H extends WithoutParamWithReturnDetachHandler<P>> P execute(@NonNull Class<H> handlerDataType) {
        return DetachUtil.getHandler(handlerDataType).handle();
    }

}
