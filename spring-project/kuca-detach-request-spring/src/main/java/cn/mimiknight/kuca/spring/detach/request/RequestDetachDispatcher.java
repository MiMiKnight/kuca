package cn.mimiknight.kuca.spring.detach.request;

import cn.mimiknight.kuca.proto.detach.dispatcher.SingleDetachHandleDispatcher;
import cn.mimiknight.kuca.spring.detach.request.executor.RequestDetachHandleExecutor;
import cn.mimiknight.kuca.spring.detach.request.handler.RequestDetachHandler;
import lombok.NonNull;
import org.springframework.beans.factory.InitializingBean;

/**
 * controller detach dispatcher
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-23 08:07:04
 */
public abstract class RequestDetachDispatcher extends SingleDetachHandleDispatcher<RequestDetachHandleExecutor> implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        setExecutor(RequestDetachHandleExecutor.class);
    }

    public <P, Q, H extends RequestDetachHandler<Q, P>> P handle(@NonNull H handler, Q param) {
        return executor.execute(handler, param);
    }

    public <P, Q, H extends RequestDetachHandler<Q, P>> P handle(@NonNull Class<H> handlerDataType, Q param) {
        return executor.execute(handlerDataType, param);
    }

}
