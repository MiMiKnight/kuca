package cn.mimiknight.developer.kuca.spring.appeasy.utils;

import cn.mimiknight.developer.kuca.proto.api.errorcode.AbstractKucaErrorReturnFactory;
import cn.mimiknight.developer.kuca.proto.api.errorcode.exception.KucaErrorCodeException;
import cn.mimiknight.developer.kuca.proto.api.errorcode.exception.KucaErrorCodeReuseException;
import cn.mimiknight.developer.kuca.proto.api.errorcode.exception.KucaErrorCodeUndefinedException;
import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorReturn;
import cn.mimiknight.developer.kuca.spring.api.common.utils.KucaSpringContextUtils;
import cn.mimiknight.developer.kuca.spring.appeasy.KucaAppEasyProperties;
import cn.mimiknight.developer.kuca.spring.appeasy.exception.KucaServiceException;
import cn.mimiknight.developer.kuca.spring.appeasy.model.KucaERPPair;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 错误码加载工具类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-23 14:03:28
 */
public class KucaERUtils {

    private static final Map<String, KucaERPPair> callRecords = new HashMap<>(128);

    private KucaERUtils() {
    }

    /**
     * load
     *
     * @param code code
     * @return {@link IKucaErrorReturn }
     */
    public static IKucaErrorReturn load(String code) {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];
        return isCheckErrorCodeReuse(code, () -> checkErrorCodeReuse(code, stackTrace));
    }

    /**
     * load error return
     *
     * @param code code
     * @return {@link IKucaErrorReturn }
     */
    private static IKucaErrorReturn loadErrorReturn(String code) {
        AbstractKucaErrorReturnFactory errorReturnFactory = KucaSpringContextUtils.getBean(AbstractKucaErrorReturnFactory.class);
        try {
            return errorReturnFactory.getErrorReturn(code);
        } catch (KucaErrorCodeException e) {
            throw new KucaErrorCodeUndefinedException("Error Code Undefined Exception", code);
        }
    }

    /**
     * call fingerprint
     *
     * @param stack stack
     * @return {@link String }
     */
    private static String getCallFingerprint(StackTraceElement stack) {
        String result = stack.getClassName() +
                stack.getMethodName() +
                stack.getLineNumber();
        return sha256(result);
    }

    /**
     * get call location
     *
     * @param stack stack
     * @return {@link String }
     */
    private static String getCallLocation(StackTraceElement stack) {
        return stack.getClassName() + ":" + stack.getLineNumber();
    }

    /**
     * check error code reuse
     *
     * @param code       code
     * @param stackTrace stack trace
     * @return {@link IKucaErrorReturn }
     */
    private static IKucaErrorReturn checkErrorCodeReuse(String code, StackTraceElement stackTrace) {
        String fingerprint = getCallFingerprint(stackTrace);
        KucaERPPair pair = callRecords.get(code);
        if (null != pair) {
            // 错误码重复使用异常（错误码相同，则调用指纹必定相同）
            if (!fingerprint.equals(pair.getLeft())) {
                throw new KucaErrorCodeReuseException("Error Code Reuse Exception", code, getCallLocation(stackTrace));
            }
            return pair.getRight();
        }
        IKucaErrorReturn errorReturn = loadErrorReturn(code);
        callRecords.put(code, new KucaERPPair(fingerprint, errorReturn));
        return errorReturn;
    }

    /**
     * is check error code reuse
     *
     * @param code     code
     * @param supplier supplier
     * @return {@link IKucaErrorReturn }
     */
    private static IKucaErrorReturn isCheckErrorCodeReuse(String code, Supplier<IKucaErrorReturn> supplier) {
        // 是否检查错误码重复使用
        KucaAppEasyProperties config = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class);
        boolean checkReuse = config.getErrorCode().isCheckReuse();
        if (!checkReuse) {
            return loadErrorReturn(code);
        }
        return supplier.get();
    }

    /**
     * sha256
     *
     * @param target target
     * @return {@link String }
     */
    private static String sha256(String target) {
        if (StringUtils.isEmpty(target)) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(target.getBytes(StandardCharsets.UTF_8));
            return byte2Hex(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new KucaServiceException("Get sha256 message digest failed", e);
        }
    }

    /**
     * byte2 hex
     *
     * @param bytes bytes
     * @return {@link String }
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte data : bytes) {
            sb.append(Integer.toString((data & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }


}
