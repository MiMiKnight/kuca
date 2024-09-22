package cn.mimiknight.developer.kuca.spring.ecology.model.validator;

/**
 * response validator
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-05-30 23:37:24
 * @since v1.0
 */
public interface ResponseValidator<T> extends ParamValidator<T> {

    /**
     * valid
     *
     * @param param param
     */
    void valid(T param);
}
