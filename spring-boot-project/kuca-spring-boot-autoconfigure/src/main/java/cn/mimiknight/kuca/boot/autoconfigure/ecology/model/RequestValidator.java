package cn.mimiknight.kuca.boot.autoconfigure.ecology.model;

/**
 * request validator
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-05-30 23:37:41
 * @since v1.0
 */
public interface RequestValidator<T> extends ParamValidator<T> {

    /**
     * valid
     *
     * @param param param
     */
    void valid(T param);
}
