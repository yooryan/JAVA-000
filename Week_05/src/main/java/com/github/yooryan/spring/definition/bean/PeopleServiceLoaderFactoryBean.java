package com.github.yooryan.spring.definition.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * ServiceLoaderFactoryBean
 *
 * @author linyunrui
 */
@Configuration
public class PeopleServiceLoaderFactoryBean extends ServiceLoaderFactoryBean {

    @PostConstruct
    public void setServiceType() {
        super.setServiceType(DefaultPeopleFactory.class);
    }
}
