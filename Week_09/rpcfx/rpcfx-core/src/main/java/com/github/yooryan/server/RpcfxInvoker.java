package com.github.yooryan.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.yooryan.api.RpcfxRequest;
import com.github.yooryan.api.RpcfxResolver;
import com.github.yooryan.api.RpcfxResponse;
import com.github.yooryan.exception.RpcFxException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver){
        this.resolver = resolver;
    }

    public <T> RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        // 作业1：改成泛型和反射
     //   Object service = resolver.resolve(serviceClass);//this.applicationContext.getBean(serviceClass);
        final Class<?> serviceClass = request.getServiceClass();
        final T service = (T) resolver.resolve(serviceClass);
        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            return response;
        } catch ( IllegalAccessException | InvocationTargetException e) {

            // 3.Xstream

            // 2.封装一个统一的RpcfxException
            // 客户端也需要判断异常
            e.printStackTrace();
            final RpcFxException rpcFxException = new RpcFxException("RpcfxInvoker#invoke failed : "+e.getMessage(),e);
            response.setException(rpcFxException);
            response.setStatus(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
