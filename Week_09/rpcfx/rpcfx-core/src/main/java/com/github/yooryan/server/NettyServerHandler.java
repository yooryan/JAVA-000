package com.github.yooryan.server;

import com.alibaba.fastjson.JSON;
import com.github.yooryan.api.RpcfxRequest;
import com.github.yooryan.api.RpcfxResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author linyunrui
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private final RpcfxInvoker invoker;

    public NettyServerHandler(RpcfxInvoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpContent content = (HttpContent)msg;
        final String byteBuf = content.content().toString(UTF_8);
        //RpcfxRqeust反序列化
        RpcfxRequest rpcRequest = JSON.parseObject(byteBuf ,RpcfxRequest.class);
        log.info("RpcfxRequest serializer : " + rpcRequest.toString());
        final RpcfxResponse response = invoker.invoke(rpcRequest);
        final String responseJson = JSON.toJSONString(response);

        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(responseJson.getBytes()));
        httpResponse.headers().set("Content-Type", "application/json");
        httpResponse.headers().setInt("Content-Length", responseJson.length());
        ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE).sync();

    }
}
