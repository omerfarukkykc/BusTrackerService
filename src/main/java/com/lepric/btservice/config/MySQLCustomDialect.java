package com.lepric.btservice.config;

import org.hibernate.spatial.dialect.mysql.MySQL56SpatialDialect;

public class MySQLCustomDialect extends MySQL56SpatialDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin";
    }
}