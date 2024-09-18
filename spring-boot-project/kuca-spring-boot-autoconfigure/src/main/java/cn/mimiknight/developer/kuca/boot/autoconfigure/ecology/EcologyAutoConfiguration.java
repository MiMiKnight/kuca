package cn.mimiknight.developer.kuca.boot.autoconfigure.ecology;

import cn.mimiknight.developer.kuca.spring.ecology.EcologyParamValidationProcessor;
import cn.mimiknight.developer.kuca.spring.ecology.EcologyProperties;
import cn.mimiknight.developer.kuca.spring.ecology.EcologyRequestExecutor;
import cn.mimiknight.developer.kuca.spring.ecology.handler.EcologyRequestHandlerBox;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * ecology auto configuration
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-31 22:08:56
 * @since v1.0
 */
@AutoConfiguration
@Import(value = {EcologyRequestExecutor.class, EcologyRequestHandlerBox.class,
        EcologyParamValidationProcessor.class})
public class EcologyAutoConfiguration {

    @Bean
    @ConditionalOnClass(EcologyProperties.class)
    @ConfigurationProperties(prefix = "kuca.ecology", ignoreInvalidFields = true)
    public EcologyProperties getEcologyProperties() {
        return new EcologyProperties();
    }
}
