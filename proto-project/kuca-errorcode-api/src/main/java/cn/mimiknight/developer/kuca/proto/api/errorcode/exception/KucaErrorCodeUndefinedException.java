package cn.mimiknight.developer.kuca.proto.api.errorcode.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * kuca error code not found exception
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-22 20:48:40
 */
@Getter
public class KucaErrorCodeUndefinedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1404195234238113609L;

    private final String errorCode;

    /**
     * kuca error code undefined exception
     *
     * @param message   message
     * @param errorCode errorCode
     */
    public KucaErrorCodeUndefinedException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
