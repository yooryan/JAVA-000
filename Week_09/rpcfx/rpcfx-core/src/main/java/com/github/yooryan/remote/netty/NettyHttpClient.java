package com.github.yooryan.remote.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.yooryan.api.RpcfxRequest;
import com.github.yooryan.api.RpcfxResponse;
import com.github.yooryan.protocol.RpcfxDecoder;
import com.github.yooryan.protocol.RpcfxEncoder;
import com.github.yooryan.remote.RemoteClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import jdk.nashorn.internal.ir.ReturnNode;
import okhttp3.MediaType;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

/**
 * @author linyunrui
 */
public class NettyHttpClient implements RemoteClient {

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            URI uri = new URI(url);
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<NioSocketChannel>() {

                        @Override
                        protected void initChannel(NioSocketChannel channel)
                                throws Exception {
                            //channel.pipeline().addLast(new HttpRequestEncoder());
                            //channel.pipeline().addLast(new HttpResponseDecoder());
                            channel.pipeline().addLast(new HttpClientCodec());
                            channel.pipeline().addLast(new HttpObjectAggregator(65536));
                            channel.pipeline().addLast(new HttpContentDecompressor());
                            channel.pipeline().addLast(new HttpClientHandler(req,uri,countDownLatch));
                        }
                    });

            ChannelFuture future = bootstrap.connect(uri.getHost(), uri.getPort()).sync();
            future.channel().closeFuture().sync();
            //接收服务端返回的数据
            countDownLatch.await();
            AttributeKey<String> key = AttributeKey.valueOf("ServerData");
            String result = future.channel().attr(key).get();
            System.out.println(result);
            return JSON.parseObject(result, RpcfxResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            group.shutdownGracefully();
        }
        return null;
    }

    private static class HttpClientHandler extends ChannelInboundHandlerAdapter {

        private final RpcfxRequest req;
        private final URI uri;
        private final CountDownLatch countDownLatch;

        public HttpClientHandler(RpcfxRequest req, URI uri,CountDownLatch countDownLatch) {
            this.req = req;
            this.uri = uri;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, this.uri.toASCIIString());
            request.content().writeBytes(JSON.toJSONBytes(req, SerializerFeature.DisableCircularReferenceDetect));
            request.headers().add(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
            request.headers().add(HttpHeaderNames.CONTENT_TYPE, MediaType.get("application/json; charset=utf-8"));
            request.setMethod(HttpMethod.POST);
            request.headers().add(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
            ctx.writeAndFlush(request);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("msg -> "+msg);
            if(msg instanceof FullHttpResponse){
                FullHttpResponse response = (FullHttpResponse)msg;
                ByteBuf buf = response.content();
                String result = buf.toString(CharsetUtil.UTF_8);
             //   System.out.println("response -> "+result);
                AttributeKey<String> key = AttributeKey.valueOf("ServerData");
                ctx.channel().attr(key).set(result);
                countDownLatch.countDown();
            }
        }
    }
}

