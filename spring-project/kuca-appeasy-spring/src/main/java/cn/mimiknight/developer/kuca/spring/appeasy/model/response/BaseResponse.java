package cn.mimiknight.developer.kuca.spring.appeasy.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

/**
 * base response
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 11:48:00
 */
@Setter
@Getter
@Accessors(chain = true)
public abstract class BaseResponse<T> {

    /**
     * 状态码
     */
    @JsonProperty(value = "status", index = 1)
    private int status;

    /**
     * 错误码
     */
    @JsonProperty(value = "error_code", index = 2)
    private String errorCode;

    /**
     * 错误类型
     */
    @JsonProperty(value = "error_type", index = 3)
    private String errorType;

    /**
     * 错误提示消息
     */
    @JsonProperty(value = "error_msg", index = 4)
    private String errorMsg;

    /**
     * 响应时间戳
     * <p>
     * 时间格式：yyyy-MM-dd'T'HH:mm:ss.SSSZ
     * <p>
     * 示例：
     * <p>
     * 2024-03-03T23:41:20.899+0800  东八区2024年3月3日23时41分20秒899毫秒
     * <p>
     * 2024-03-03T23:41:20.899+0000  零时区2024年3月3日23时41分20秒899毫秒
     * <p>
     * 2024-03-03T23:41:20.899-0800  西八区2024年3月3日23时41分20秒899毫秒
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonProperty(value = "timestamp", index = 5)
    private ZonedDateTime timestamp;

    /**
     * 数据
     */
    @JsonProperty(value = "data", index = 6)
    private T data;
}
