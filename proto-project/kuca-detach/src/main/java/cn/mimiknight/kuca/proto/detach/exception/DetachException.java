package cn.mimiknight.kuca.proto.detach.exception;

/**
 * detach exception
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-05-30 22:02:37
 * @since v1.0
 */
public class DetachException extends RuntimeException {

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
