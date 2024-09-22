package cn.mimiknight.developer.kuca.proto.api.errorcode;

import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorType;

/**
 * abstract error type factory
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 17:43:35
 */
public abstract class AbstractKucaErrorTypeFactory {

    /**
     * get error type
     *
     * @param id id
     * @return {@link IKucaErrorType }
     */
    public abstract IKucaErrorType getErrorType(String id);
}
