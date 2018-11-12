package com.arc.tracker;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

@Slf4j
public class SqlQueryInterceptor extends EmptyInterceptor {

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        QueryInfoHolder.getQueryInfo().incrementSelectCount();
        log.info("SqlQueryInterceptor called");
        return false;
    }
}
