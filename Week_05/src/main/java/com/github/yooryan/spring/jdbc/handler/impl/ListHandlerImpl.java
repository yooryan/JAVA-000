package com.github.yooryan.spring.jdbc.handler.impl;

import com.github.yooryan.spring.jdbc.handler.ResultHandler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linyunrui
 */
public class ListHandlerImpl<T> implements ResultHandler<List<T>> {

    private Class<T> cls;

    public ListHandlerImpl(Class<T> cls) {
        this.cls = cls;
    }

    @Override
    public List<T> handler(ResultSet set) throws Exception {
        List<T> tList = new ArrayList<>();
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
            tList.add(t);
        }
        return tList;
    }
}
