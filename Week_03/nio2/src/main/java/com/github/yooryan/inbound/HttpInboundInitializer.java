package com.github.yooryan.inbound;

import com.github.yooryan.filter.HttpRequestFilter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {
	
	private String proxyServer;
	/**
	 * 添加不同类型的过滤器
	 */
	private final List<HttpRequestFilter> filters = new ArrayList<>();
	
	public HttpInboundInitializer(String proxyServer) {
		this.proxyServer = proxyServer;
	}


	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline p = ch.pipeline();
//		if (sslCtx != null) {
//			p.addLast(sslCtx.newHandler(ch.alloc()));
//		}
		p.addLast(new HttpServerCodec());
		//p.addLast(new HttpServerExpectContinueHandler());
		p.addLast(new HttpObjectAggregator(1024 * 1024));
		p.addLast(new HttpInboundHandler(this.proxyServer,this.filters));

	}

	public void addFilter(HttpRequestFilter filter){
		filters.add(filter);
	}
}
