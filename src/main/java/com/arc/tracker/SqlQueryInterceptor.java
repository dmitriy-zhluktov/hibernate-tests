package com.arc.tracker;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

public class SqlQueryInterceptor extends EmptyInterceptor {

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        QueryInfoHolder.getQueryInfo().incrementSelectCount();
        return false;
    }
}
