package cn.mimiknight.developer.kuca.proto.api.common.utils;


import org.apache.commons.lang3.ArrayUtils;

/**
 * kuca log utils
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-21 14:15:31
 */
public final class KucaLogUtils {

    private KucaLogUtils() {
    }

    /**
     * 构建异常日志提示信息
     * <p>
     * 如果 level<0,则打印最深层级的异常信息
     * <p>
     * 如果 level=n,n>=0,则打印指定层级的异常或者能达到的最深层级的异常
     *
     * @param e     异常
     * @param level 层级
     * @return {@link String}
     */
    public static String buildExceptionLogTip(Throwable e, int level) {
        String format = "Exception='%1$s',Message='%2$s',Location='%3$s',CauseException='%4$s'";
        String exceptionName = "";
        String message = "";
        String location = "";
        String causeExceptionName = "";

        if (null == e) {
            return String.format(format, exceptionName, message, location, causeExceptionName);
        }

        int count = 0;
        if (level != 0 && null != e.getCause()) {
            e = e.getCause();
            count++;
            while (true) {
                if (level < 0 && null == e.getCause()) {
                    break;
                }
                if (level > 0 && (count >= level || null == e.getCause())) {
                    break;
                }
                e = e.getCause();
                count++;
            }
        }

        exceptionName = e.getClass().getCanonicalName();
        message = e.getMessage();

        StackTraceElement[] stackTrace = e.getStackTrace();
        if (ArrayUtils.isNotEmpty(stackTrace)) {
            StackTraceElement element = stackTrace[0];
            String formatLocation = "%1$s#%2$s():%3$d";
            location = String.format(formatLocation, element.getClassName(), element.getMethodName(), element.getLineNumber());
        }

        if (null != e.getCause()) {
            causeExceptionName = e.getCause().getClass().getCanonicalName();
        }
        return String.format(format, exceptionName, message, location, causeExceptionName);
    }

    /**
     * 构建异常日志提示信息
     *
     * @param e 异常
     * @return {@link String}
     */
    public static String buildExceptionLogTip(Throwable e) {
        return buildExceptionLogTip(e, 0);
    }
}
