package cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard;

/**
 * error return interface
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 15:22:37
 */
public interface IKucaErrorReturn {

    /**
     * 获取错误码
     *
     * @return {@link String}
     */
    String getCode();

    /**
     * 获取错误提示消息
     *
     * @return {@link String}
     */
    String getMessage();

    /**
     * 获取错误类型
     *
     * @return {@link IKucaErrorType}
     */
    IKucaErrorType getType();
}
