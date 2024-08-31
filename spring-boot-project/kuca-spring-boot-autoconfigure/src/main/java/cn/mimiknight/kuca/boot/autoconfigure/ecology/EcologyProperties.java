package cn.mimiknight.kuca.boot.autoconfigure.ecology;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ecology properties
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-31 22:08:46
 * @since v1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "kuca.ecology", ignoreInvalidFields = true)
public class EcologyProperties {

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
