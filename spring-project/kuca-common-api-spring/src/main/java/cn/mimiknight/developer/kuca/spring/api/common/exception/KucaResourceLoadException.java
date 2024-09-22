package cn.mimiknight.developer.kuca.spring.api.common.exception;

import java.io.Serial;


/**
 * kuca resource load exception
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-22 08:06:08
 */
public class KucaResourceLoadException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2404195234238113609L;

    public KucaResourceLoadException(String message) {
        super(message);
    }

    public KucaResourceLoadException(String message, Object... args) {
        super(String.format(message, args));
    }

    public KucaResourceLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public KucaResourceLoadException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }

}
