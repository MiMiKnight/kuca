package cn.mimiknight.developer.kuca.proto.errorcode;

import cn.mimiknight.developer.kuca.proto.errorcode.model.ErrorCode;
import cn.mimiknight.developer.kuca.proto.errorcode.model.ErrorReturn;
import cn.mimiknight.developer.kuca.proto.errorcode.model.ErrorType;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * error code configuration
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-06-01 16:01:52
 * @since v1.0
 */
@Getter
@Setter
public class ErrorCodeConfiguration {

    /**
     * error code prefix enable
     */
    private boolean prefixEnable;

    /**
     * error code prefix
     */
    private String prefix;

    /**
     * error type map
     */
    private ConcurrentHashMap<String, ErrorType> errorTypeMap;

    /**
     * error code map
     */
    private ConcurrentHashMap<String, ErrorCode> errorCodeMap;

    /**
     * error return map
     */
    private ConcurrentHashMap<String, ErrorReturn> errorReturnMap;

    public ErrorCodeConfiguration() {
        this.prefixEnable = false;
        this.prefix = "";
        this.errorTypeMap = new ConcurrentHashMap<>(128);
        this.errorCodeMap = new ConcurrentHashMap<>(128);
        this.errorReturnMap = new ConcurrentHashMap<>(128);
    }

}
