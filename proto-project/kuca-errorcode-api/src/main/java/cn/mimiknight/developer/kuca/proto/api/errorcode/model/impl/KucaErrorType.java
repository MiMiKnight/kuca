package cn.mimiknight.developer.kuca.proto.api.errorcode.model.impl;

import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorType;
import lombok.Setter;

/**
 * kuca error type
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 17:59:06
 */
@Setter
public final class KucaErrorType implements IKucaErrorType {

    private String id;

    private int status;

    private String desc;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
