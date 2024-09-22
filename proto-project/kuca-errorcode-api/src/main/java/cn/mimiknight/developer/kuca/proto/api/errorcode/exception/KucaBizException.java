package cn.mimiknight.developer.kuca.proto.api.errorcode.exception;

import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorReturn;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.util.function.Supplier;

/**
 * 自定义业务异常
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 16:56:25
 */
@Getter
public class KucaBizException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1404195234238113619L;

    /**
     * 错误返回枚举对象
     */
    private final IKucaErrorReturn errorReturn;

    /**
     * 消息
     */
    private String message;

    /**
     * 消息参数
     */
    private Object[] args;

    /**
     * 载荷
     */
    private final Object payload;

    /**
     * 构造方法
     *
     * @param errorReturn 异常返回对象
     * @param payload     载荷
     */
    public KucaBizException(IKucaErrorReturn errorReturn, Object payload) {
        super(errorReturn.getMessage());
        this.message = errorReturn.getMessage();
        this.errorReturn = errorReturn;
        this.payload = payload;
    }

    /**
     * 构造方法
     *
     * @param errorReturn   异常返回对象
     * @param payloadAction 获取载荷的函数式接口
     */
    public KucaBizException(IKucaErrorReturn errorReturn, Supplier<Object> payloadAction) {
        this(errorReturn, payloadAction.get());
    }

    /**
     * 构造方法
     *
     * @param errorReturn 异常返回对象
     */
    public KucaBizException(IKucaErrorReturn errorReturn) {
        this(errorReturn, Object::new);
    }

    /**
     * 构造方法
     *
     * @param errorReturn 异常返回对象
     * @param cause       原因
     * @param payload     载荷
     */
    public KucaBizException(IKucaErrorReturn errorReturn, Throwable cause, Object payload) {
        super(errorReturn.getMessage(), cause);
        this.message = errorReturn.getMessage();
        this.errorReturn = errorReturn;
        this.payload = payload;
    }

    /**
     * 构造方法
     *
     * @param errorReturn   异常返回对象
     * @param cause         原因
     * @param payloadAction 获取载荷的函数式接口
     */
    public KucaBizException(IKucaErrorReturn errorReturn, Throwable cause, Supplier<Object> payloadAction) {
        this(errorReturn, cause, payloadAction.get());
    }


    /**
     * 构造方法
     *
     * @param errorReturn 异常返回对象
     * @param cause       原因
     */
    public KucaBizException(IKucaErrorReturn errorReturn, Throwable cause) {
        this(errorReturn, cause, Object::new);
    }

    /**
     * 消息参数赋值，构建异常对象
     *
     * @param args 消息参数
     * @return {@link KucaBizException}
     */
    public KucaBizException build(Object... args) {
        this.args = args;
        this.message = buildMsg(message, args);
        return this;
    }

    /**
     * 消息参数赋值，构建异常对象
     *
     * @param argsAction 获取消息参数函数式接口
     * @return {@link KucaBizException}
     */
    public KucaBizException build(Supplier<Object[]> argsAction) {
        return build(argsAction.get());
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * 构建获取异常消息
     *
     * @param format 字符串模板
     * @param args   参数
     * @return {@link String}
     */
    private static String buildMsg(String format, Object... args) {
        if (StringUtils.isNotBlank(format) && ArrayUtils.isNotEmpty(args)) {
            return String.format(format, args);
        }
        return format;
    }
}

