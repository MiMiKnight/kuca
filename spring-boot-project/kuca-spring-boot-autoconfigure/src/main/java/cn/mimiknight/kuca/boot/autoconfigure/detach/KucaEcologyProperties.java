package cn.mimiknight.kuca.boot.autoconfigure.detach;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "kuca.ecology", ignoreInvalidFields = true)
public class KucaEcologyProperties {

}
