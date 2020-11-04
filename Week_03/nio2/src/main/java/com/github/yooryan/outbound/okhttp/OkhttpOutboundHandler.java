package com.github.yooryan.outbound.okhttp;

import com.github.yooryan.outbound.OutboundHandler;
import com.github.yooryan.outbound.httpclient4.HttpOutboundHandler;
import com.github.yooryan.outbound.httpclient4.NamedThreadFactory;
import com.github.yooryan.util.ExceptionResponseUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import kotlin.Pair;
import okhttp3.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author linyunrui
 */
public class OkhttpOutboundHandler implements OutboundHandler {

    private OkHttpClient okHttpClient;
    private ExecutorService proxyService;
    private String backendUrl;

    private OkhttpOutboundHandler(Builder builder) {
        this.okHttpClient = builder.okHttpClient;
        this.proxyService = builder.proxyService;
        this.backendUrl = builder.backendUrl;
    }

    public static class Builder {
        private OkHttpClient okHttpClient;
        private ExecutorService proxyService;
        private String backendUrl;

        public Builder backendUrl(String backendUrl) {
            this.backendUrl = backendUrl.endsWith("/") ? backendUrl.substring(0, backendUrl.length() - 1) : backendUrl;
            return this;
        }

        public OkhttpOutboundHandler build() {
            int cores = Runtime.getRuntime().availableProcessors() * 2;
            long keepAliveTime = 1000;
            int queueSize = 2048;
            RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();

            //线程池
            proxyService = new ThreadPoolExecutor(cores, cores,
                    keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                    new NamedThreadFactory("proxyService"), handler);
            okHttpClient = new OkHttpClient();
            return new OkhttpOutboundHandler(this);
        }

    }

    @Override
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        final String url = this.backendUrl + fullRequest.uri();
        proxyService.submit(() -> fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        Headers.Builder headersBuilder = new Headers.Builder();
        for (Map.Entry<String, String> header : inbound.headers()) {
            headersBuilder.add(header.getKey(),header.getValue());
        }
        Headers build = headersBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .headers(headersBuilder.build())
                .build();//GET by default
        Headers headers = request.headers();
        Headers.Builder builder = headers.newBuilder();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try (ResponseBody responseBody = response.body()) {
                    if (response.isSuccessful()) {
                        handleResponse(inbound, ctx, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final Response endpointResponse) throws Exception {
        FullHttpResponse response = null;
        try {
        byte[] bytes = endpointResponse.body().bytes();

        response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(bytes));
        response.headers().set("Content-Type", "application/json");
        response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.header("Content-Length")));
        }catch (Exception e){
            e.printStackTrace();
            response = (FullHttpResponse) ExceptionResponseUtil.exceptionResponseHandler(ctx,e);
            exceptionCaught(ctx, e);
        }finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }

    }




    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {


    }

}
