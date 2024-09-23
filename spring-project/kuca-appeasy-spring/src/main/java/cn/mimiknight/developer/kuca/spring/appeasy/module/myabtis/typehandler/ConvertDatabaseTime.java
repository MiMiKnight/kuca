package cn.mimiknight.developer.kuca.spring.appeasy.module.myabtis.typehandler;

import cn.mimiknight.developer.kuca.spring.appeasy.constant.KucaAppEasyConstant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 转换数据库时间接口
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-10-11 22:31:08
 */
public interface ConvertDatabaseTime<T> {

    /**
     * 数据库时间时区
     */
    String DB_TIME_ZONE = KucaAppEasyConstant.Database.TIME_ZONE;

    /**
     * 数据库日期格式
     */
    String DATE_TIME_FORMAT = KucaAppEasyConstant.Database.DATE_TIME_FORMAT;

    /**
     * 默认日期格式化对象
     */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    /**
     * 数据库时间按指定时区转换
     *
     * @param dbDateTime 数据库时间
     * @return {@link T}
     */
    T convert(LocalDateTime dbDateTime);
}
