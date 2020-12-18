package com.github.yooryan.remote.okhttp;

import com.alibaba.fastjson.JSON;
import com.github.yooryan.api.RpcfxRequest;
import com.github.yooryan.api.RpcfxResponse;
import com.github.yooryan.remote.RemoteClient;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * @author linyunrui
 */
public class OkHttpClient implements RemoteClient {


    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    private final okhttp3.OkHttpClient client = OkHttpClientObject.CLIENT.getClientInstance();

    @Override
    public RpcfxResponse post(final RpcfxRequest req, final String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);
        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson = client.newCall(request).execute().body().string();
        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }
}
