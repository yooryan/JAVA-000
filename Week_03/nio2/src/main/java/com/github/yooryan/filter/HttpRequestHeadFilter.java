package com.github.yooryan.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

import java.util.HashMap;

/**
 * @author linyunrui
 */
public class HttpRequestHeadFilter implements HttpRequestFilter{

    private final HttpHeaders headers;


    public HttpRequestHeadFilter(HttpHeaders headers) {
        this.headers = headers;
    }

    public HttpRequestHeadFilter() {
        this(null);
    }

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        if (headers != null){
            fullRequest.headers().add(headers);
        }
    }
}
