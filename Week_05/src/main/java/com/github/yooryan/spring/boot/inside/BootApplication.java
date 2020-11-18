/*
package com.github.yooryan.spring.boot.inside;

import com.github.yooryan.spring.boot.Klass;
import com.github.yooryan.spring.boot.School;
import com.github.yooryan.spring.boot.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

*/
/**
 * 启动类单独特意放在一个单独的包内，这样就不会通过@ComponentScan扫面到当前包的配置类
 * @author linyunrui
 *//*

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BootApplication.class, args);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName ==>" + beanDefinitionName);
        }
        Klass klassBean = context.getBean(Klass.class);
        School schoolBean = context.getBean(School.class);
        Student studentBean = context.getBean(Student.class);
        System.out.println("klassBean ==>" + klassBean);
        System.out.println("schoolBean ==>" + schoolBean);
        System.out.println("studentBean ==>" + studentBean.getName());
    }

}
*/
