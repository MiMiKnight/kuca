package cn.mimiknight.developer.kuca.proto.api.errorcode.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * 错误码重复使用异常
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-23 13:17:43
 */
@Getter
public class KucaErrorCodeReuseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1404195234238113609L;

    /**
     * error code
     */
    private final String errorCode;
    
    /**
     * location
     */
    private final String location;

    public KucaErrorCodeReuseException(String message, String errorCode, String location) {
        super(message);
        this.errorCode = errorCode;
        this.location = location;
    }

}
