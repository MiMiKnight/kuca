package cn.mimiknight.developer.kuca.spring.appeasy.module.myabtis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.OffsetDateTimeTypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * 自定义{@link OffsetDateTime}时间类型转换器
 * <p>
 * 支持自定义入数据库时间格式及时区
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-23 19:52:46
 */
public class KucaOffsetDateTimeTypeHandler extends OffsetDateTimeTypeHandler implements ConvertDatabaseTime<OffsetDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, OffsetDateTime parameter, JdbcType jdbcType) throws SQLException {
        if (null == parameter) {
            ps.setObject(i, null);
            return;
        }
        OffsetDateTime dateTime = parameter.withOffsetSameInstant(ZoneOffset.of(DB_TIME_ZONE));
        String strDateTime = dateTime.format(DATE_TIME_FORMATTER);
        ps.setString(i, strDateTime);
    }

    @Override
    public OffsetDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        LocalDateTime result = rs.getObject(columnName, LocalDateTime.class);
        return convert(result);
    }

    @Override
    public OffsetDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        LocalDateTime result = rs.getObject(columnIndex, LocalDateTime.class);
        return convert(result);
    }

    @Override
    public OffsetDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        LocalDateTime result = cs.getObject(columnIndex, LocalDateTime.class);
        return convert(result);
    }

    @Override
    public OffsetDateTime convert(LocalDateTime dbDateTime) {
        if (null == dbDateTime) {
            return null;
        }
        return OffsetDateTime.of(dbDateTime, ZoneOffset.of(DB_TIME_ZONE));
    }

}