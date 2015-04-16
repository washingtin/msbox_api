package com.msbox.api.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by root on 15-3-31.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    public Object determineCurrentLookupKey() {
        return  DbContextHolder.getDbType();
    }
}
