package cn.mimiknight.developer.kuca.proto.api.common.utils;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * kuca use me utils
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-23 20:22:46
 */
public final class KucaUtils {

    private KucaUtils() {
    }

    /**
     * 随机id
     *
     * @return {@link String}
     */
    public static long randomId(long start, long end) {
        return ThreadLocalRandom.current().nextLong(start, end);
    }

    /**
     * 带有日期属性的随机id
     *
     * @param start 开始
     * @param end   结束
     * @return {@link String}
     */
    public static String randomDateId(long start, long end) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT"));
        String datetime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        return datetime + randomId(start, end);
    }

    /**
     * 带有日期属性的随机id
     * <p>
     * start 默认值 100000000
     * <p>
     * end 默认值 999999999
     *
     * @return {@link String}
     */
    public static String randomDateId() {
        return randomDateId(100000000, 999999999);
    }

}
