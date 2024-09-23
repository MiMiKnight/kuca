package cn.mimiknight.developer.kuca.spring.appeasy.utils;

import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorReturn;
import cn.mimiknight.developer.kuca.spring.api.common.utils.KucaResourceLoadUtils;
import cn.mimiknight.developer.kuca.spring.api.common.utils.KucaSpringContextUtils;
import cn.mimiknight.developer.kuca.spring.appeasy.KucaAppEasyProperties;
import cn.mimiknight.developer.kuca.spring.appeasy.exception.KucaServiceException;
import cn.mimiknight.developer.kuca.spring.appeasy.model.response.KucaServiceResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 系统公共工具类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2024-01-20 19:18:32
 */
public final class KucaAppEasyUtils {

    private KucaAppEasyUtils() {
    }

    /**
     * 获取项目唯一标识（错误码前缀）
     *
     * @return {@link String}
     */
    public static String getAppId() {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        return config.getAppId();
    }

    /**
     * 拼接错误码前缀，获取完整的错误码
     *
     * @param code code
     * @return {@link String}
     */
    public static String getFullErrorCode(String code) {
        String appId = getAppId();
        return (StringUtils.isBlank(appId) ? "" : appId) + code;
    }

    /**
     * 构建服务响应
     *
     * @param errorReturn 错误返回对象
     * @param data        数据
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildServiceResponse(IKucaErrorReturn errorReturn, Object data, Object[] args) {
        KucaServiceResponse response = new KucaServiceResponse();
        response.setStatus(errorReturn.getType().getStatus());
        response.setErrorType(errorReturn.getType().getDesc());
        response.setErrorCode(getFullErrorCode(errorReturn.getCode()));
        String message = errorReturn.getMessage();
        if (!Objects.isNull(args) && ArrayUtils.isNotEmpty(args)) {
            message = String.format(message, args);
        }
        response.setErrorMsg(message);
        response.setData(data);
        response.setTimestamp(ZonedDateTime.now());
        return response;
    }

    /**
     * 构建服务响应
     *
     * @param errorReturn 错误返回对象
     * @param data        数据
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildServiceResponse(IKucaErrorReturn errorReturn, Object data) {
        return buildServiceResponse(errorReturn, data, null);
    }

    /**
     * 构建服务响应
     *
     * @param errorReturn 错误返回对象
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildServiceResponse(IKucaErrorReturn errorReturn) {
        return buildServiceResponse(errorReturn, new Object());
    }

    /**
     * 构建服务响应
     *
     * @param errorReturn error return
     * @param data        数据
     * @param args        args
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildServiceResponse(IKucaErrorReturn errorReturn, Supplier<Object> data, Object... args) {
        return buildServiceResponse(errorReturn, data.get(), args);
    }

    /**
     * 构建Ok服务响应
     *
     * @param data 数据
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildOkServiceResponse(Object data) {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        String errorCode = config.getErrorCode().getOk();
        IKucaErrorReturn errorReturn = KucaERUtils.load(errorCode);
        return buildServiceResponse(errorReturn, () -> data);
    }

    /**
     * 构建Bad异常时服务响应
     *
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildBadServiceResponse() {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        String errorCode = config.getErrorCode().getBad();
        IKucaErrorReturn errorReturn = KucaERUtils.load(errorCode);
        return buildServiceResponse(errorReturn, Object::new);
    }

    /**
     * 构建接口404异常时服务响应
     *
     * @param data 数据
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildApi404ServiceResponse(Supplier<Object> data) {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        String errorCode = config.getErrorCode().getApi404();
        IKucaErrorReturn errorReturn = KucaERUtils.load(errorCode);
        return buildServiceResponse(errorReturn, data.get());
    }

    /**
     * 构建空指针异常时服务响应
     *
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildNullPointServiceResponse() {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        String errorCode = config.getErrorCode().getNullPoint();
        IKucaErrorReturn errorReturn = KucaERUtils.load(errorCode);
        return buildServiceResponse(errorReturn, Object::new);
    }

    /**
     * 构建媒体类型不支持异常时服务响应
     *
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildMediaTypeNotSupportedServiceResponse(Supplier<Object> data) {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        String errorCode = config.getErrorCode().getMediaTypeNotSupported();
        IKucaErrorReturn errorReturn = KucaERUtils.load(errorCode);
        return buildServiceResponse(errorReturn, data.get());
    }

    /**
     * 构建HTTP消息不可读异常时服务响应
     *
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildHttpMessageNotReadableServiceResponse(Supplier<Object> data) {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        String errorCode = config.getErrorCode().getHttpMessageNotReadable();
        IKucaErrorReturn errorReturn = KucaERUtils.load(errorCode);
        return buildServiceResponse(errorReturn, data.get());
    }

    /**
     * 构建HTTP请求方法不支持异常时服务响应
     *
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildHttpRequestMethodNotSupportedServiceResponse(Supplier<Object> data) {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        String errorCode = config.getErrorCode().getHttpRequestMethodNotSupported();
        IKucaErrorReturn errorReturn = KucaERUtils.load(errorCode);
        return buildServiceResponse(errorReturn, data.get());
    }

    /**
     * 构建错误码未定义异常时服务响应
     *
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildErrorCodeUndefinedServiceResponse(Supplier<Object> data) {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        String errorCode = config.getErrorCode().getErrorCodeUndefined();
        IKucaErrorReturn errorReturn = KucaERUtils.load(errorCode);
        return buildServiceResponse(errorReturn, data.get());
    }

    /**
     * 构建错误码重复使用异常时服务响应
     *
     * @return {@link KucaServiceResponse}
     */
    public static KucaServiceResponse buildErrorCodeReuseServiceResponse(Supplier<Object> data) {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        String errorCode = config.getErrorCode().getErrorCodeReuse();
        IKucaErrorReturn errorReturn = KucaERUtils.load(errorCode);
        return buildServiceResponse(errorReturn, data.get());
    }


    /**
     * load error code xml
     *
     * @param xmlLocation xml location
     * @return {@link Document }
     */
    public static Document loadErrorCodeXml(String xmlLocation) {
        File config = KucaResourceLoadUtils.load(xmlLocation);
        try {
            return Jsoup.parse(config, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new KucaServiceException("load 'error code config xml' failed,file location = %1$s", xmlLocation);
        }
    }

    /**
     * load error code xml
     *
     * @return {@link Document }
     */
    public static Document loadErrorCodeXml() {
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        String location = config.getErrorCode().getConfigLocation();
        return loadErrorCodeXml(location);
    }

}
