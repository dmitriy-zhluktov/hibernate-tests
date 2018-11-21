package com.arc.tracker;

import org.hibernate.resource.jdbc.spi.StatementInspector;

public class SqlQueryInspector implements StatementInspector {

    @Override
    public String inspect(String sql) {
        QueryInfoHolder.getQueryInfo().incrementSelectCount();
        return sql;
    }
}
