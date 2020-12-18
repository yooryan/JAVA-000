package com.github.yooryan.remote.netty;

import com.github.yooryan.api.RpcfxRequest;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.AttributeKey;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter {

   private FullHttpRequest fullHttpRequest;
   private RpcfxRequest req;
   private ChannelHandlerContext context;
   private int contentLength = 0;

    public NettyHttpClientOutboundHandler(RpcfxRequest req) {
        this.fullHttpRequest = fullHttpRequest;
        this.req = req;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println(msg);
        AttributeKey<Object> key = AttributeKey.valueOf("ServerData");
        ctx.channel().attr(key).set(msg);

        //把客户端的通道关闭
        ctx.channel().close();
      /*  if(msg instanceof HttpContent) {
            HttpContent content = (HttpContent)msg;
            final String byteBuf = content.content().toString(UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(byteBuf.getBytes()));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", byteBuf.length());
            context.write(response).addListener(ChannelFutureListener.CLOSE);
            context.flush();
        }*/
    }

}