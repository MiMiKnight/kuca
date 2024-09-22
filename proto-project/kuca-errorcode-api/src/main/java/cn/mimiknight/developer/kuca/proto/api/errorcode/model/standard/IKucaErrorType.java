package cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard;

/**
 * error type interface
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 15:22:16
 */
public interface IKucaErrorType {

    /**
     * 错误类型唯一标识
     *
     * @return {@link String }
     */
    String getId();

    /**
     * 获取状态码
     *
     * @return {@link String}
     */
    int getStatus();

    /**
     * 获取错误类型描述
     *
     * @return {@link String}
     */
    String getDesc();

}
