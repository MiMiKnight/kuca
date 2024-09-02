package cn.mimiknight.developer.kuca.proto.detach.dispatcher;

import cn.mimiknight.developer.kuca.proto.detach.executor.DetachHandleExecutor;

/**
 * single detach handle dispatcher
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-23 08:13:27
 */
public abstract class AbstractSingleDetachHandleDispatcher<E extends DetachHandleExecutor> extends AbstractGeneralDetachHandleDispatcher {

    protected E executor;

    protected void setExecutor(Class<E> executorDataType) {
        this.executor = executor(executorDataType);
    }
}
