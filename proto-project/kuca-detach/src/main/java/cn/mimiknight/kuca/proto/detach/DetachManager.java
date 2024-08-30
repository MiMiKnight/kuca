package cn.mimiknight.kuca.proto.detach;

import cn.mimiknight.kuca.proto.detach.executor.DetachHandleExecutor;
import cn.mimiknight.kuca.proto.detach.handler.DetachHandler;
import lombok.Getter;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * detach manager
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-22 20:24:15
 */
@Getter
public class DetachManager {

    private static final int INIT_CAPACITY = 128;

    private final ConcurrentHashMap<Class<? extends DetachHandler>, DetachHandler> handlerMappings;

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
        DetachManager manager = DetachManagerFactory.getManager();
        if (Objects.isNull(manager)) {
            manager = DetachManagerFactory.create();
        }
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
        DetachManager manager = DetachManagerFactory.getManager();
        if (Objects.isNull(manager)) {
            manager = DetachManagerFactory.create();
        }
        manager.getExecutorMappings().put(executorDataType, executor);
        return manager;
    }

}
