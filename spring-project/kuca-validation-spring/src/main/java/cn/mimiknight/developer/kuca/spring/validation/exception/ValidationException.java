package cn.mimiknight.developer.kuca.spring.validation.exception;

/**
 * validation exception
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-19 15:08:43
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException() {
        super();
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
