package cn.mimiknight.kuca.boot.autoconfigure.ecology;

import cn.mimiknight.kuca.spring.ecology.EcologyConfig;
import cn.mimiknight.kuca.spring.ecology.EcologyManager;
import cn.mimiknight.kuca.spring.ecology.EcologyManagerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * ecology auto configuration
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-31 22:08:56
 * @since v1.0
 */
@AutoConfiguration
@EnableConfigurationProperties(value = {EcologyProperties.class})
public class EcologyAutoConfiguration {

    @Bean("ecologyManager")
    @ConditionalOnMissingBean(value = {EcologyManager.class})
    public EcologyManager getEcologyManager(EcologyProperties properties) {
        EcologyManager manager = EcologyManagerFactory.create();
        EcologyConfig config = manager.getConfig();
        config.setRequestValidation(EcologyMapStruct.INSTANCE.convert(properties.getRequestValidation()));
        config.setResponseValidation(EcologyMapStruct.INSTANCE.convert(properties.getResponseValidation()));
        manager.setConfig(config);
        return manager;
    }
}
