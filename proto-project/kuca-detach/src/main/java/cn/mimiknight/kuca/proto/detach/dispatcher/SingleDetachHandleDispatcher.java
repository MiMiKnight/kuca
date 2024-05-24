package cn.mimiknight.kuca.proto.detach.dispatcher;

import cn.mimiknight.kuca.proto.detach.executor.DetachHandleExecutor;

/**
 * single detach handle dispatcher
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-23 08:13:27
 */
public abstract class SingleDetachHandleDispatcher<E extends DetachHandleExecutor> extends GeneralDetachHandleDispatcher {

    protected E executor;

    protected void setExecutor(Class<E> executorDataType) {
        this.executor = executor(executorDataType);
    }
}
