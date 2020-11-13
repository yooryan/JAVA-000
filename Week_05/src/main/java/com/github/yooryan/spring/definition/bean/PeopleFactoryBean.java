package com.github.yooryan.spring.definition.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linyunrui
 */
@Configuration
public class PeopleFactoryBean implements FactoryBean<People> {

    public People getObject() throws Exception {
        People people = new People();
        people.setId(4L);
        people.setName("name4");
        return people;
    }

    public Class<?> getObjectType() {
        return null;
    }
}
