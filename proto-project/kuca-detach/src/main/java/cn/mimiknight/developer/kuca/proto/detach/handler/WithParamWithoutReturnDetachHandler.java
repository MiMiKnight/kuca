package cn.mimiknight.developer.kuca.proto.detach.handler;

/**
 * with param and return detach handler interface
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-22 20:36:55
 */
public interface WithParamWithoutReturnDetachHandler<Q> extends DetachHandler {

    /**
     * handle
     *
     * @param param param
     */
    void handle(Q param);
}
