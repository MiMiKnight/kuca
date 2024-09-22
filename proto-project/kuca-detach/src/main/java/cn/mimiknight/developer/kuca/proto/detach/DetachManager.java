package cn.mimiknight.developer.kuca.proto.detach;

import cn.mimiknight.developer.kuca.proto.detach.executor.DetachHandleExecutor;
import cn.mimiknight.developer.kuca.proto.detach.handler.DetachHandler;
import lombok.Getter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * detach manager
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-22 20:24:15
 */
public class DetachManager {

    @Getter
    private static final int INIT_CAPACITY = 128;

    @Getter
    private final ConcurrentHashMap<Class<? extends DetachHandler>, DetachHandler> handlerMappings;

    @Getter
    private final ConcurrentHashMap<Class<? extends DetachHandleExecutor>, DetachHandleExecutor> executorMappings;

    public DetachManager() {
        this.handlerMappings = new ConcurrentHashMap<>(INIT_CAPACITY);
        this.executorMappings = new ConcurrentHashMap<>(INIT_CAPACITY);
    }

    /**
     * put handlerMappings
     *
     * @param handlerDataType handler data type
     * @param handler         handler
     * @return {@link DetachManager }
     */
    public synchronized DetachManager putHandlerMapping(Class<? extends DetachHandler> handlerDataType,
                                                        DetachHandler handler) {
        DetachManager manager = DetachManagerFactory.getDetachManager();
        manager.getHandlerMappings().put(handlerDataType, handler);
        return manager;
    }

    /**
     * put executor mapping
     *
     * @param executorDataType executor data type
     * @param executor         executor
     * @return {@link DetachManager }
     */
    public synchronized DetachManager putExecutorMapping(Class<? extends DetachHandleExecutor> executorDataType,
                                                         DetachHandleExecutor executor) {
        DetachManager manager = DetachManagerFactory.getDetachManager();
        manager.getExecutorMappings().put(executorDataType, executor);
        return manager;
    }

}
