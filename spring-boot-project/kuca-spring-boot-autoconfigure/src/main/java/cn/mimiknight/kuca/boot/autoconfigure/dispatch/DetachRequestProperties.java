package cn.mimiknight.kuca.boot.autoconfigure.dispatch;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "kuca.detach", ignoreInvalidFields = true)
public class DetachRequestProperties {

}
