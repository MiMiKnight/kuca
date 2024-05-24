package cn.mimiknight.kuca.proto.detach;

import cn.mimiknight.kuca.proto.detach.executor.DetachHandleExecutor;
import cn.mimiknight.kuca.proto.detach.filter.DetachFilter;
import cn.mimiknight.kuca.proto.detach.handler.DetachHandler;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * detach manager
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-22 20:24:15
 */
public class DetachManager {

    private static final int INIT_CAPACITY = 128;

    @Getter
    private final ConcurrentHashMap<Class<? extends DetachHandler>, DetachHandler> handlerMappings;

    @Getter
    private final ConcurrentHashMap<Class<? extends DetachHandleExecutor>, DetachHandleExecutor> executorMappings;

    @Getter
    private final ConcurrentHashMap<Class<? extends DetachHandler>, LinkedList<DetachFilter>> filterMappings;

    public DetachManager() {
        this.handlerMappings = new ConcurrentHashMap<>(INIT_CAPACITY);
        this.executorMappings = new ConcurrentHashMap<>(INIT_CAPACITY);
        this.filterMappings = new ConcurrentHashMap<>(INIT_CAPACITY);
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

    /**
     * put filter mappings
     *
     * @param filterDataType filter data type
     * @param filter         filter
     * @return {@link DetachManager }
     */
    public synchronized DetachManager putFilterMappings(Class<? extends DetachHandler> filterDataType,
                                                        DetachFilter filter) {
        DetachManager manager = DetachManagerFactory.getManager();
        if (Objects.isNull(manager)) {
            manager = DetachManagerFactory.create();
        }
        manager.getFilterMappings().compute(filterDataType, (k, v) -> {
            if (Objects.isNull(v)) {
                v = new LinkedList<>();
            }
            v.add(filter);
            return v;
        });
        return manager;
    }

}
