package io.xmacedo.utils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {

    public static <T> T getOrNull(ResultSet rs, String columnLabel, Class<T> returnType) throws SQLException {
        return getOrDefault(rs, columnLabel, returnType, null);
    }

    public static <T> T getOrDefault(ResultSet rs, String columnLabel, Class<T> returnType, T defaultIfNull) throws SQLException {
        Object value = rs.getObject(columnLabel);
        if(value instanceof BigDecimal)
            return (T) Integer.valueOf(value.toString());

        return value != null ? returnType.cast(value) : defaultIfNull;
    }

    private Dao() {

    }

}
