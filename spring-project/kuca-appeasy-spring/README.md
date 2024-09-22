# usage
```java
package cn.mimiknight.developer.kuca.spring.appeasy;

import cn.mimiknight.developer.kuca.proto.api.errorcode.AbstractKucaErrorReturnFactory;
import cn.mimiknight.developer.kuca.proto.errorcode.processor.GeneralKucaErrorReturnFactory;
import cn.mimiknight.developer.kuca.spring.api.common.utils.KucaResourceLoadUtils;
import cn.mimiknight.developer.kuca.spring.api.common.utils.KucaSpringContextUtils;
import cn.mimiknight.developer.kuca.spring.appeasy.aspect.KucaDecryptRequestBodyAdvice;
import cn.mimiknight.developer.kuca.spring.appeasy.aspect.KucaEncryptResponseBodyAdvice;
import cn.mimiknight.developer.kuca.spring.appeasy.aspect.KucaGlobalExceptionHandlerAdvice;
import cn.mimiknight.developer.kuca.spring.appeasy.aspect.KucaUnifyResponseBodyStructureAdvice;
import cn.mimiknight.developer.kuca.spring.appeasy.utils.KucaAppEasyUtils;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({KucaUnifyResponseBodyStructureAdvice.class, KucaDecryptRequestBodyAdvice.class,
        KucaEncryptResponseBodyAdvice.class, KucaGlobalExceptionHandlerAdvice.class,
        KucaSpringContextUtils.class, KucaResourceLoadUtils.class})
@Configuration
public class AppConfig {

    @Bean
    public KucaAppEasyProperties getKucaAppEasyProperties() {
        KucaAppEasyProperties config = new KucaAppEasyProperties();
        config.setAppId("HD.127");
        KucaAppEasyProperties.ErrorCode errorCode = new KucaAppEasyProperties.ErrorCode();
        errorCode.setOk("000000");
        errorCode.setBad("999999");
        errorCode.setApi404("999998");
        errorCode.setNullPoint("999997");
        errorCode.setHttpMessageNotReadable("999996");
        errorCode.setMediaTypeNotSupported("999995");
        config.setErrorCode(errorCode);
        return config;
    }

    @Bean
    public AbstractKucaErrorReturnFactory getKucaErrorReturnFactory() {
        Document document = KucaAppEasyUtils.loadErrorCodeXml();
        return GeneralKucaErrorReturnFactory.create(document);
    }

}
```