package cn.mimiknight.kuca.spring.detach.request.executor;

import cn.mimiknight.kuca.proto.detach.executor.DetachHandleExecutor;
import cn.mimiknight.kuca.proto.detach.utils.DetachUtil;
import cn.mimiknight.kuca.spring.detach.request.handler.RequestDetachHandler;
import lombok.NonNull;

public class RequestDetachHandleExecutor implements DetachHandleExecutor {

    public <P, Q, H extends RequestDetachHandler<Q, P>> P execute(@NonNull H handler, Q param) {
        return handler.handle(param);
    }

    public <P, Q, H extends RequestDetachHandler<Q, P>> P execute(@NonNull Class<H> handlerDataType, Q param) {
        return DetachUtil.getHandler(handlerDataType).handle(param);
    }
}
