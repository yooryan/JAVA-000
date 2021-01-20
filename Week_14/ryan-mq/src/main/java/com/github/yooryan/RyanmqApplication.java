package com.github.yooryan;

import com.github.yooryan.core.RyanBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author linyunrui
 */
@SpringBootApplication
public class RyanmqApplication {

    @Bean
    public RyanBroker ryanBroker(){
        return new RyanBroker();
    }

    public static void main(String[] args) {
        SpringApplication.run(RyanmqApplication.class,args);
    }
}
