# usage

## inject bean
```java
@Configuration
public class MyConfig {

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