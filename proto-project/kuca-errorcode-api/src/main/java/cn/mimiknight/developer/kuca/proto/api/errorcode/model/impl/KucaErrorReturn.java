package cn.mimiknight.developer.kuca.proto.api.errorcode.model.impl;

import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorReturn;
import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorType;
import lombok.Setter;

/**
 * kuca error return
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 17:59:12
 */
@Setter
public final class KucaErrorReturn implements IKucaErrorReturn {

    private String code;

    private String message;

    private IKucaErrorType type;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public IKucaErrorType getType() {
        return type;
    }
}
