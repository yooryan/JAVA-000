package com.github.yooryan.netty.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author linyunrui
 */
public class NettyClient {

    public void client() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8808/test")
                .build();

        Response response = client.newCall(request).execute();{
            if (response.isSuccessful()){
                System.out.println(response.body().string());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NettyClient nettyClient = new NettyClient();
        nettyClient.client();;
    }
}
