package com.github.yooryan.api;

public interface RpcfxResolver {

    Object resolve(String serviceClass);

    <T> T resolve(Class<T> aClass);

}
