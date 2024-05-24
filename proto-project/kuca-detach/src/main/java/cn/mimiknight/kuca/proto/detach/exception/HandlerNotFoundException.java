package cn.mimiknight.kuca.proto.detach.exception;

import java.io.Serial;

/**
 * handler not found exception
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-22 22:42:48
 */
public class HandlerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4037785770038127776L;

    public HandlerNotFoundException() {
    }

    public HandlerNotFoundException(String message) {
        super(message);
    }

    public HandlerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerNotFoundException(Throwable cause) {
        super(cause);
    }
}
