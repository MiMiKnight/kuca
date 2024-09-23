package cn.mimiknight.developer.kuca.spring.appeasy.aspect;

import cn.mimiknight.developer.kuca.proto.api.common.utils.KucaLogUtils;
import cn.mimiknight.developer.kuca.proto.api.common.utils.KucaMsgPayloadUtils;
import cn.mimiknight.developer.kuca.proto.api.errorcode.exception.KucaBizException;
import cn.mimiknight.developer.kuca.proto.api.errorcode.exception.KucaErrorCodeReuseException;
import cn.mimiknight.developer.kuca.proto.api.errorcode.exception.KucaErrorCodeUndefinedException;
import cn.mimiknight.developer.kuca.spring.appeasy.exception.KucaServiceException;
import cn.mimiknight.developer.kuca.spring.appeasy.model.response.KucaServiceResponse;
import cn.mimiknight.developer.kuca.spring.appeasy.utils.KucaAppEasyUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * kuca global exception handler advice
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 14:11:17
 */
@Slf4j
@RestControllerAdvice
public class KucaGlobalExceptionHandlerAdvice implements Ordered {

    private static final String API_PATH_KEY = "api_path";

    @Override
    public int getOrder() {
        return Byte.MAX_VALUE;
    }

    /**
     * Throwable
     *
     * @param e {@link Throwable}异常
     * @return {@link KucaServiceResponse }
     */
    @ExceptionHandler(value = Throwable.class)
    public KucaServiceResponse handle(Throwable e) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildBadServiceResponse();
    }

    /**
     * Exception
     *
     * @param e {@link Exception}异常
     * @return {@link KucaServiceResponse }
     */
    @ExceptionHandler(value = Exception.class)
    public KucaServiceResponse handle(Exception e) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildBadServiceResponse();
    }

    /**
     * RuntimeException
     *
     * @param e {@link RuntimeException}异常
     * @return {@link KucaServiceResponse }
     */
    @ExceptionHandler(value = RuntimeException.class)
    public KucaServiceResponse handle(RuntimeException e) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildBadServiceResponse();
    }

    /**
     * NullPointerException异常处理
     *
     * @param e {@link NullPointerException}异常
     * @return {@link KucaServiceResponse}
     */
    @ExceptionHandler(value = NullPointerException.class)
    public KucaServiceResponse handle(NullPointerException e) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildNullPointServiceResponse();
    }

    /**
     * NoHandlerFoundException异常处理
     *
     * @param e {@link NoHandlerFoundException}异常
     * @return {@link KucaServiceResponse}
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public KucaServiceResponse handle(NoHandlerFoundException e, HttpServletRequest request) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildApi404ServiceResponse(() ->
                KucaMsgPayloadUtils.load(API_PATH_KEY, request.getRequestURI())
                        .finished()
        );
    }

    /**
     * HttpMediaTypeNotSupportedException异常处理
     *
     * @param e {@link HttpMediaTypeNotSupportedException}异常
     * @return {@link KucaServiceResponse}
     */
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public KucaServiceResponse handle(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildMediaTypeNotSupportedServiceResponse(() ->
                KucaMsgPayloadUtils.load(API_PATH_KEY, request.getRequestURI())
                        .finished()
        );
    }

    /**
     * HttpMessageNotReadableException异常处理
     *
     * @param e {@link HttpMessageNotReadableException}异常
     * @return {@link KucaServiceResponse}
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public KucaServiceResponse handle(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildHttpMessageNotReadableServiceResponse(() ->
                KucaMsgPayloadUtils.load(API_PATH_KEY, request.getRequestURI())
                        .finished()
        );
    }

    /**
     * HttpRequestMethodNotSupportedException异常处理
     *
     * @param e {@link HttpRequestMethodNotSupportedException}异常
     * @return {@link KucaServiceResponse}
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public KucaServiceResponse handle(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildHttpRequestMethodNotSupportedServiceResponse(() ->
                KucaMsgPayloadUtils.load("method", request.getMethod())
                        .load(API_PATH_KEY, request.getRequestURI())
                        .finished()
        );
    }

    /**
     * 错误码未定义异常处理
     *
     * @param e {@link KucaErrorCodeUndefinedException}异常
     * @return {@link KucaServiceResponse }
     */
    @ExceptionHandler(value = KucaErrorCodeUndefinedException.class)
    public KucaServiceResponse handle(KucaErrorCodeUndefinedException e, HttpServletRequest request) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildErrorCodeUndefinedServiceResponse(() ->
                KucaMsgPayloadUtils.load("error_code", e.getErrorCode())
                        .load(API_PATH_KEY, request.getRequestURI())
                        .finished());
    }

    /**
     * 错误码重复使用异常处理
     *
     * @param e {@link KucaErrorCodeReuseException}异常
     * @return {@link KucaServiceResponse }
     */
    @ExceptionHandler(value = KucaErrorCodeReuseException.class)
    public KucaServiceResponse handle(KucaErrorCodeReuseException e, HttpServletRequest request) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildErrorCodeReuseServiceResponse(() ->
                KucaMsgPayloadUtils.load("error_code", e.getErrorCode())
                        .load("location", e.getLocation())
                        .load(API_PATH_KEY, request.getRequestURI())
                        .finished());
    }

    /**
     * 自定义服务异常处理
     *
     * @param e {@link KucaServiceException}异常
     * @return {@link KucaServiceResponse }
     */
    @ExceptionHandler(value = KucaServiceException.class)
    public KucaServiceResponse handle(KucaServiceException e) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildBadServiceResponse();
    }

    /**
     * 自定义业务异常处理
     *
     * @param e {@link KucaBizException}异常
     * @return {@link KucaServiceResponse }
     */
    @ExceptionHandler(value = KucaBizException.class)
    public KucaServiceResponse handle(KucaBizException e) {
        log.error(KucaLogUtils.buildExceptionLogTip(e));
        return KucaAppEasyUtils.buildServiceResponse(e.getErrorReturn());
    }

}
