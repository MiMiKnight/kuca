package cn.mimiknight.developer.kuca.proto.detach.handler;

/**
 * without param with return detach handler
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-22 20:37:58
 */
public interface WithoutParamWithReturnDetachHandler<P> extends DetachHandler {

    /**
     * handle
     *
     * @return {@link P }
     */
    P handle();
}
