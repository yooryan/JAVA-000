package com.github.yooryan.primarysecondary.config;

import com.github.yooryan.primarysecondary.context.DBType;
import com.github.yooryan.primarysecondary.dbswitch.Primary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linyunrui
 */
@Configuration
public class DataSourcesConfig {

    /**
     * 主库配置
     * @return
     */
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Bean
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 从库配置
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 从库配置
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary2")
    public DataSource secondaryDataSource2(){
        return DataSourceBuilder.create().build();
    }


    @Bean
    public DataSource routingDataSources(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                                          @Qualifier("secondaryDataSource") DataSource secondaryDataSource,
                                          @Qualifier("secondaryDataSource2") DataSource secondaryDataSource2) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBType.PRIMARY, primaryDataSource);
        targetDataSources.put(DBType.SECONDARY, secondaryDataSource);
        targetDataSources.put(DBType.SECONDARY2, secondaryDataSource2);
        RoutingDataSources routingDataSources = new RoutingDataSources();
        routingDataSources.setDefaultTargetDataSource(primaryDataSource);
        routingDataSources.setTargetDataSources(targetDataSources);
        return routingDataSources;
    }


}
