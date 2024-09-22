package cn.mimiknight.developer.kuca.spring.ecology.exception;

/**
 * Request重复绑定异常
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-31 16:30:00
 * @since v1.0
 */
public class RequestRepeatBindException extends RuntimeException {
    private static final long serialVersionUID = 4937610828908578505L;

    public RequestRepeatBindException() {
        super();
    }

    public RequestRepeatBindException(String message) {
        super(message);
    }

    public RequestRepeatBindException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestRepeatBindException(Throwable cause) {
        super(cause);
    }
}
