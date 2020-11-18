package com.github.yooryan.spring.jdbc.handler.impl;

import com.github.yooryan.spring.jdbc.handler.ResultHandler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;

/**
 * @author linyunrui
 */
public class ObjectHandlerImpl<T> implements ResultHandler<T> {

    private Class<T> cls;

    public ObjectHandlerImpl(Class<T> cls) {
        this.cls = cls;
    }

    @Override
    public T handler(ResultSet set) throws Exception {
        T t = cls.newInstance();
        while (set.next()){
            BeanInfo beanInfo = Introspector.getBeanInfo(cls, Object.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String name = propertyDescriptor.getName();
                Object value = set.getObject(name);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(t,value);
            }
        }
        return t;
    }
}
