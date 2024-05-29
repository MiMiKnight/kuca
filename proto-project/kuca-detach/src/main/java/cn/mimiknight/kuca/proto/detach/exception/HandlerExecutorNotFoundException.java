package cn.mimiknight.kuca.proto.detach.exception;

/**
 * handler executor not found exception
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-05-22 22:52:40
 */
public class HandlerExecutorNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4037785770038127776L;

    public HandlerExecutorNotFoundException() {
    }

    public HandlerExecutorNotFoundException(String message) {
        super(message);
    }

    public HandlerExecutorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerExecutorNotFoundException(Throwable cause) {
        super(cause);
    }
}
