package cn.mimiknight.developer.kuca.boot.autoconfigure.ecology;

import cn.mimiknight.developer.kuca.spring.ecology.KucaEcologyParamValidationProcessor;
import cn.mimiknight.developer.kuca.spring.ecology.KucaEcologyProperties;
import cn.mimiknight.developer.kuca.spring.ecology.KucaEcologyRequestExecutor;
import cn.mimiknight.developer.kuca.spring.ecology.handler.KucaEcologyRequestHandlerBox;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * kuca ecology auto configuration
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-22 15:32:24
 */
@AutoConfiguration
@ConditionalOnClass(KucaEcologyProperties.class)
@Import(value = {KucaEcologyRequestExecutor.class, KucaEcologyRequestHandlerBox.class,
        KucaEcologyParamValidationProcessor.class})
public class KucaEcologyAutoConfiguration {

    @Bean
    @ConditionalOnClass(KucaEcologyProperties.class)
    @ConfigurationProperties(prefix = "kuca.ecology", ignoreInvalidFields = true)
    public KucaEcologyProperties getEcologyProperties() {
        return new KucaEcologyProperties();
    }

}
