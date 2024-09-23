package cn.mimiknight.developer.kuca.spring.appeasy.module.myabtis.typehandler;

import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 自定义
 * {@link Date}
 * 时间类型转换器
 * <p>
 * 支持自定义入数据库时间格式及时区
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-23 19:52:46
 */
public class KucaDateTypeHandler extends DateTypeHandler implements ConvertDatabaseTime<Date> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        if (null == parameter) {
            ps.setObject(i, null);
            return;
        }
        Instant instant = parameter.toInstant();
        ZonedDateTime dateTime = ZonedDateTime.ofInstant(instant, ZoneId.of(DB_TIME_ZONE));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.ENGLISH);
        String strDateTime = dateTime.format(formatter);
        ps.setObject(i, strDateTime);
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        LocalDateTime result = rs.getObject(columnName, LocalDateTime.class);
        return convert(result);
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        LocalDateTime result = rs.getObject(columnIndex, LocalDateTime.class);
        return convert(result);
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        LocalDateTime result = cs.getObject(columnIndex, LocalDateTime.class);
        return convert(result);
    }


    @Override
    public Date convert(LocalDateTime dbDateTime) {
        if (null == dbDateTime) {
            return null;
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dbDateTime, ZoneId.of(DB_TIME_ZONE));
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}
