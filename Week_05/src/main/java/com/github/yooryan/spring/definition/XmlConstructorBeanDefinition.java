package com.github.yooryan.spring.definition;

import com.github.yooryan.spring.definition.bean.People;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 构造器配置
 * @author linyunrui
 */
public class XmlConstructorBeanDefinition {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:bean-definitions-constructor-context.xml");
        //启动上下文
        applicationContext.refresh();
        //依赖查找
        People bean = applicationContext.getBean(People.class);
        System.out.println(bean.toString());
        // 关闭Spring上下文
        applicationContext.close();
    }
}
