package cn.mimiknight.kuca.proto.detach.dispatcher;

import cn.mimiknight.kuca.proto.detach.executor.DetachHandleExecutor;
import cn.mimiknight.kuca.proto.detach.utils.DetachUtil;

/**
 * general detach handle dispatcher
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-23 08:06:48
 */
public abstract class GeneralDetachHandleDispatcher {

    /**
     * get executor
     *
     * @param executorDataType executor data type
     * @return {@link E }
     */
    protected <E extends DetachHandleExecutor> E executor(Class<E> executorDataType) {
        return DetachUtil.getExecutor(executorDataType);
    }
}
