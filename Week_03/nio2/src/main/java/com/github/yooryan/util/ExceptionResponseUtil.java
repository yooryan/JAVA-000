package com.github.yooryan.util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponse;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author linyunrui
 */
public class ExceptionResponseUtil {

    public static HttpResponse exceptionResponseHandler(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
        return new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);

    }
}
