package cn.mimiknight.kuca.boot.autoconfigure.detach;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(value = {KucaDetachProperties.class})
public class KucaDetachAutoConfiguration {
}
