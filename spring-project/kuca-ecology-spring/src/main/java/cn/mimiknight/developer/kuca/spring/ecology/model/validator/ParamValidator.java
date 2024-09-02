package cn.mimiknight.developer.kuca.spring.ecology.model.validator;

/**
 * 参数校验接口
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-03-09 11:44:17
 */
public interface ParamValidator<T> {

    /**
     * 校验方法
     *
     * @param param 待校验参数
     */
    void valid(T param);
}
