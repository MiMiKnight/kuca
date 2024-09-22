package cn.mimiknight.developer.kuca.proto.detach.exception;

import java.io.Serial;

/**
 * detach exception
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-05-30 22:02:37
 * @since v1.0
 */
public class DetachException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4037785770038127776L;

    public DetachException() {
    }

    public DetachException(String message) {
        super(message);
    }

    public DetachException(String message, Throwable cause) {
        super(message, cause);
    }

    public DetachException(Throwable cause) {
        super(cause);
    }
}
