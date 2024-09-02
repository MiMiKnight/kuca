package cn.mimiknight.developer.kuca.proto.detach.utils;

import cn.mimiknight.developer.kuca.proto.detach.DetachManager;
import cn.mimiknight.developer.kuca.proto.detach.DetachManagerFactory;
import cn.mimiknight.developer.kuca.proto.detach.exception.DetachException;
import cn.mimiknight.developer.kuca.proto.detach.executor.DetachHandleExecutor;
import cn.mimiknight.developer.kuca.proto.detach.handler.DetachHandler;

import java.util.Objects;

/**
 * detach util
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-22 23:40:46
 */
public final class DetachUtil {

    private DetachUtil() {
    }

    /**
     * check handler
     *
     * @param handler         handler
     * @param handlerDataType handler data type
     */
    public static void checkHandler(DetachHandler handler,
                                    Class<? extends DetachHandler> handlerDataType) {
        String expectHandlerType = handlerDataType.getCanonicalName();
        if (Objects.isNull(handler)) {
            String format = "Handler not found which is '%s'";
            String tip = String.format(format, expectHandlerType);
            throw new DetachException(tip);
        }
        if (!handlerDataType.isInstance(handler)) {
            String actualHandlerType = handler.getClass().getCanonicalName();
            String format = "Handler date type not matched,expect '%s' but '%s'";
            String tip = String.format(format, expectHandlerType, actualHandlerType);
            throw new IllegalArgumentException(tip);
        }
    }

    /**
     * check executor
     *
     * @param executor         executor
     * @param executorDataType executor data type
     */
    public static void checkExecutor(DetachHandleExecutor executor,
                                     Class<? extends DetachHandleExecutor> executorDataType) {
        String executorHandlerType = executorDataType.getCanonicalName();
        if (Objects.isNull(executor)) {
            String format = "Handler executor not found which is '%s'";
            String tip = String.format(format, executorHandlerType);
            throw new DetachException(tip);
        }
        if (!executorDataType.isInstance(executor)) {
            String actualExecutorType = executor.getClass().getCanonicalName();
            String format = "Handler executor date type not matched,expect '%s' but '%s'";
            String tip = String.format(format, executorDataType, actualExecutorType);
            throw new IllegalArgumentException(tip);
        }
    }

    /**
     * check detach manager
     */
    public static void checkDetachManager() {
        if (Objects.isNull(DetachManagerFactory.getDetachManager())) {
            throw new DetachException("DetachManager object is not exist,please create it first.");
        }
    }

    /**
     * get handler
     *
     * @param handlerDataType handler data type
     * @return {@link H }
     */
    public static <H extends DetachHandler> H getHandler(Class<H> handlerDataType) {
        checkDetachManager();
        DetachManager manager = DetachManagerFactory.getDetachManager();
        DetachHandler handler = manager.getHandlerMappings().get(handlerDataType);
        checkHandler(handler, handlerDataType);
        return handlerDataType.cast(handler);
    }

    /**
     * get executor
     *
     * @param executorDataType executor data type
     * @return {@link E }
     */
    public static <E extends DetachHandleExecutor> E getExecutor(Class<E> executorDataType) {
        checkDetachManager();
        DetachManager manager = DetachManagerFactory.getDetachManager();
        DetachHandleExecutor executor = manager.getExecutorMappings().get(executorDataType);
        checkExecutor(executor, executorDataType);
        return executorDataType.cast(executor);
    }
}
