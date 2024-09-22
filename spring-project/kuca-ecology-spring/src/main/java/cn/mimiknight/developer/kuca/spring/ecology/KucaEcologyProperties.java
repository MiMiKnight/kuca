package cn.mimiknight.developer.kuca.spring.ecology;

import lombok.Getter;
import lombok.Setter;

/**
 * ecology config
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-31 21:48:32
 * @since v1.0
 */
@Getter
@Setter
public class KucaEcologyProperties {

    private RequestValidation requestValidation = new RequestValidation();
    private ResponseValidation responseValidation = new ResponseValidation();

    @Getter
    @Setter
    public static class RequestValidation {

        /**
         * 请求参数校验开关
         */
        private boolean enable = true;

    }

    @Getter
    @Setter
    public static class ResponseValidation {

        /**
         * 响应参数校验开关
         */
        private boolean enable = true;

    }
}
