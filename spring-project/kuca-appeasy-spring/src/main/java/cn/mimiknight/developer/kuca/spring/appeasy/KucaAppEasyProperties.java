package cn.mimiknight.developer.kuca.spring.appeasy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KucaAppEasyProperties {

    /**
     * 项目ID
     * <p>
     * 作为错误码前缀
     */
    private String appId;

    /**
     * 错误码配置对象
     */
    private ErrorCode errorCode = new ErrorCode();

    /**
     * 统一响应体配置
     */
    private UnifyResponseBodyConfig unifyResponseBody = new UnifyResponseBodyConfig();

    /**
     * 异常处理器配置
     */
    private ExceptionHandlerConfig exceptionHandler = new ExceptionHandlerConfig();

    /**
     * 异常处理器配置
     */
    private DatabaseConfig database = new DatabaseConfig();

    @Getter
    @Setter
    public static class DatabaseConfig {

        /**
         * 数据库日期格式
         */
        private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss.SSSSSSSSS";

        /**
         * 数据库时区
         */
        private String timezone = "GMT";
    }

    @Getter
    @Setter
    public static class ExceptionHandlerConfig {

        /**
         * 默认全局异常处理器开关
         */
        private boolean enable = true;
    }

    @Getter
    @Setter
    public static class UnifyResponseBodyConfig {

        /**
         * 响应体切面配置开关
         */
        private boolean enable = true;

        /**
         * 切面执行顺序
         */
        private int order = Byte.MAX_VALUE;
    }

    @Getter
    @Setter
    public static class ErrorCode {

        /**
         * 错误码配置文件位置
         */
        private String configLocation = "classpath:error-code-config.xml";

        /**
         * 默认OK错误码
         */
        private String ok;

        /**
         * 默认Bad错误码
         */
        private String bad;

        /**
         * 接口路径404错误码
         */
        private String api404;

        /**
         * 空指针错误码
         */
        private String nullPoint;

        /**
         * 媒体类型不支持错误码
         */
        private String mediaTypeNotSupported;

        /**
         * Http消息不可读错误码
         */
        private String httpMessageNotReadable;

        /**
         * HTTP请求方法不支持错误码
         */
        private String httpRequestMethodNotSupported;

        /**
         * 错误码未定义错误码
         */
        private String errorCodeUndefined;

        /**
         * 错误码重复使用错误码
         */
        private String errorCodeReuse;

    }

}
