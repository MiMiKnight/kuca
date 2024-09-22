package cn.mimiknight.developer.kuca.proto.api.errorcode;

import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorReturn;

/**
 * abstract error return factory
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 17:43:32
 */
public abstract class AbstractKucaErrorReturnFactory {

    /**
     * get error return
     *
     * @param code code
     * @return {@link IKucaErrorReturn }
     */
    public abstract IKucaErrorReturn getErrorReturn(String code);
}
