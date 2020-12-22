package com.github.yooryan.api;

import lombok.Data;

/**
 * @author linyunrui
 */
@Data
public class RpcfxRequest<T> {

    private Class<T> serviceClass;

    private String method;

    private Object[] params;

}
