package com.github.yooryan.primarysecondary.config;

import com.github.yooryan.primarysecondary.context.DBType;
import com.github.yooryan.primarysecondary.context.DBTypeHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author linyunrui
 */
public class RoutingDataSources extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DBType dbType = DBTypeHolder.get();
        DBTypeHolder.remove();
        return dbType;
    }
}
