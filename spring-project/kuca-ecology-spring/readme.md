# usage

## inject bean
```java
@Configuration
public class MyConfig {

    @Bean
    @ConfigurationProperties(prefix = "kuca.ecology", ignoreInvalidFields = true)
    public EcologyProperties getEcologyProperties() {
        return new EcologyProperties();
    }

    @Bean
    public EcologyRequestExecutor getEcologyRequestExecutor() {
        return new EcologyRequestExecutor();
    }

    @Bean
    public EcologyParamValidationProcessor getEcologyParamValidationProcessor() {
        return new EcologyParamValidationProcessor();
    }

    @Bean
    public EcologyRequestHandlerBox getEcologyRequestHandlerBox() {
        return new EcologyRequestHandlerBox();
    }
}
```