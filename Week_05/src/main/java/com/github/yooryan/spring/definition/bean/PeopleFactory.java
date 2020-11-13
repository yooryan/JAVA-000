package com.github.yooryan.spring.definition.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linyunrui
 */
@Configuration
public class PeopleFactory {

    @Bean
    public static People createBean(){
        People people = new People();
        people.setId(3L);
        people.setName("name3");
        return people;
    }
}
