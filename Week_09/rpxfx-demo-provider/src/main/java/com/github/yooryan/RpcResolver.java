/*
package com.github.yooryan;

import com.github.yooryan.api.RpcfxResolver;

public class RpcResolver implements RpcfxResolver {

    @Override
    public Object resolve(String serviceClass) {
        try {
            final Class<?> aClass = Class.forName(serviceClass);
            return aClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    return null;
    }

    @Override
    public <T> T resolve(Class<T> aClass) {
        final T t;
        try {
            t = aClass.newInstance();
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
*/
