package cn.mimiknight.developer.kuca.proto.api.errorcode.exception;

import java.io.Serial;

/**
 * kuca error code not found exception
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-22 20:48:40
 */
public class KucaErrorCodeUndefinedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1404195234238113609L;

    /**
     * kuca error code not found exception
     *
     * @param code 错误码
     */
    public KucaErrorCodeUndefinedException(String code) {
        super(code);
    }

}
