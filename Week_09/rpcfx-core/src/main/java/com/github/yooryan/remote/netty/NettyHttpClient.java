package com.github.yooryan.remote.netty;

import com.github.yooryan.api.RpcfxRequest;
import com.github.yooryan.api.RpcfxResponse;
import com.github.yooryan.remote.RemoteClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.util.AttributeKey;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ThreadFactory;

/**
 * @author linyunrui
 */
public class NettyHttpClient implements RemoteClient {

    @Override
    public RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            //NioSocketChannel.class
            b.channel(NioSocketChannel.class);
            b.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                public void initChannel(NioSocketChannel ch) throws Exception {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    //客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new NettyHttpClientOutboundHandler(req));
                }
            });


            URI uri = new URI(url);
            // Start the client.
            ChannelFuture f = b.connect(uri.getHost(), uri.getPort()).sync();

            //发送数据给服务器
            f.channel().writeAndFlush(req);

            // f.channel().write(fullRequest);
            //  f.channel().flush();
            f.channel().closeFuture().sync();
            //接收服务端返回的数据
            AttributeKey<String> key = AttributeKey.valueOf("ServerData");
            Object result = f.channel().attr(key).get();
            System.out.println(result.toString());

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
        return null;
    }
}
