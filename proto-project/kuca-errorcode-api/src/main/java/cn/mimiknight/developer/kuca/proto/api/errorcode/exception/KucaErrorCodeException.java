package cn.mimiknight.developer.kuca.proto.api.errorcode.exception;

import java.io.Serial;

/**
 * kuca error code exception
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-22 06:33:44
 */
public class KucaErrorCodeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1404195234238113609L;

    public KucaErrorCodeException(String message) {
        super(message);
    }

    public KucaErrorCodeException(String message, Object... args) {
        super(String.format(message, args));
    }

    public KucaErrorCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public KucaErrorCodeException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }

}
