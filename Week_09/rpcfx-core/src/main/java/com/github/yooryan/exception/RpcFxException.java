package com.github.yooryan.exception;

/**
 * @author linyunrui
 */
public class RpcFxException extends RuntimeException{

    public RpcFxException() {
        super();
    }

    public RpcFxException(String message) {
        super(message);
    }

    public RpcFxException(String message, Throwable cause) {
        super(message, cause);
    }
}
