package com.github.yooryan.outbound;


import com.github.yooryan.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author linyunrui
 */
public interface OutboundHandler {


    void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx);
}
