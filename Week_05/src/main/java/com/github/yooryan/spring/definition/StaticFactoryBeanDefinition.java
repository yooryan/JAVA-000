package com.github.yooryan.spring.definition;

import com.github.yooryan.spring.definition.bean.People;
import com.github.yooryan.spring.definition.bean.PeopleFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 静态工厂
 * @author linyunrui
 */
public class StaticFactoryBeanDefinition {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前类
        applicationContext.register(PeopleFactory.class);
        //启动应用上下文
        applicationContext.refresh();

        People bean = PeopleFactory.createBean();
        System.out.println(bean.toString());
    }
}
