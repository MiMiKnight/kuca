package cn.mimiknight.developer.kuca.boot.autoconfigure.appeasy;

import cn.mimiknight.developer.kuca.proto.api.errorcode.AbstractKucaErrorReturnFactory;
import cn.mimiknight.developer.kuca.proto.errorcode.processor.GeneralKucaErrorReturnFactory;
import cn.mimiknight.developer.kuca.spring.api.common.utils.KucaResourceLoadUtils;
import cn.mimiknight.developer.kuca.spring.api.common.utils.KucaSpringContextUtils;
import cn.mimiknight.developer.kuca.spring.appeasy.KucaAppEasyProperties;
import cn.mimiknight.developer.kuca.spring.appeasy.aspect.KucaDecryptRequestBodyAdvice;
import cn.mimiknight.developer.kuca.spring.appeasy.aspect.KucaEncryptResponseBodyAdvice;
import cn.mimiknight.developer.kuca.spring.appeasy.aspect.KucaGlobalExceptionHandlerAdvice;
import cn.mimiknight.developer.kuca.spring.appeasy.aspect.KucaUnifyResponseBodyStructureAdvice;
import cn.mimiknight.developer.kuca.spring.appeasy.utils.KucaAppEasyUtils;
import org.jsoup.nodes.Document;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * kuca app easy auto configuration
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-22 15:32:10
 */
@AutoConfiguration
@ConditionalOnClass(KucaAppEasyProperties.class)
public class KucaAppEasyAutoConfiguration {

    @Bean
    @ConditionalOnClass(KucaAppEasyProperties.class)
    @ConfigurationProperties(prefix = "kuca.appeasy", ignoreInvalidFields = true)
    public KucaAppEasyProperties getKucaAppEasyProperties() {
        return new KucaAppEasyProperties();
    }

    @Bean
    @ConditionalOnClass(KucaResourceLoadUtils.class)
    @ConditionalOnMissingBean(value = {KucaResourceLoadUtils.class})
    public KucaResourceLoadUtils getKucaResourceLoadUtils() {
        return new KucaResourceLoadUtils();
    }

    @Bean
    @ConditionalOnClass(KucaSpringContextUtils.class)
    @ConditionalOnMissingBean(value = {KucaSpringContextUtils.class})
    public KucaSpringContextUtils getKucaSpringContextUtils() {
        return new KucaSpringContextUtils();
    }

    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnMissingBean(value = {KucaEncryptResponseBodyAdvice.class})
    public KucaEncryptResponseBodyAdvice getKucaEncryptResponseBodyAdvice() {
        return new KucaEncryptResponseBodyAdvice();
    }

    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnMissingBean(value = {KucaDecryptRequestBodyAdvice.class})
    public KucaDecryptRequestBodyAdvice getKucaDecryptRequestBodyAdvice() {
        return new KucaDecryptRequestBodyAdvice();
    }

    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnMissingBean(value = {KucaGlobalExceptionHandlerAdvice.class})
    @ConditionalOnProperty(prefix = "kuca.appeasy", name = "exception-handler.enable", havingValue = "true", matchIfMissing = true)
    public KucaGlobalExceptionHandlerAdvice getKucaGlobalExceptionHandlerAdvice() {
        return new KucaGlobalExceptionHandlerAdvice();
    }

    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnMissingBean(value = {KucaUnifyResponseBodyStructureAdvice.class})
    @ConditionalOnProperty(prefix = "kuca.appeasy", name = "unify-response-body.enable", havingValue = "true", matchIfMissing = true)
    public KucaUnifyResponseBodyStructureAdvice getKucaUnifyResponseBodyStructureAdvice(KucaSpringContextUtils contextUtils) {
        return new KucaUnifyResponseBodyStructureAdvice();
    }

    @Bean
    @ConditionalOnClass(value = {AbstractKucaErrorReturnFactory.class})
    @ConditionalOnMissingBean(value = {AbstractKucaErrorReturnFactory.class})
    public AbstractKucaErrorReturnFactory getKucaErrorReturnFactory() {
        Document document = KucaAppEasyUtils.loadErrorCodeXml();
        return GeneralKucaErrorReturnFactory.create(document);
    }
}
