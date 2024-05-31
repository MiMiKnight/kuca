package cn.mimiknight.kuca.boot.autoconfigure.dispatch;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(value = {DetachRequestProperties.class})
public class DetachRequestAutoConfiguration {
}
