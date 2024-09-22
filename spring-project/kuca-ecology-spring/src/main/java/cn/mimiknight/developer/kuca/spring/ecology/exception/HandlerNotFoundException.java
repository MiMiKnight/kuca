package cn.mimiknight.developer.kuca.spring.ecology.exception;

/**
 * Handler未找到异常
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-31 16:29:15
 * @since v1.0
 */
public class HandlerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4037785770038127776L;

    public HandlerNotFoundException() {
        super();
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
