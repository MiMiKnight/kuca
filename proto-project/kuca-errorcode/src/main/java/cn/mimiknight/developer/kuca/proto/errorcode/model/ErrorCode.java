package cn.mimiknight.developer.kuca.proto.errorcode.model;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * error type
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-06-01 15:59:53
 * @since v1.0
 */
@Getter
@Accessors(chain = true)
public class ErrorCode {


    /**
     * id
     */
    private String id;

    /**
     * type id
     */
    private String typeId;

    /**
     * desc
     */
    private String desc;
}
