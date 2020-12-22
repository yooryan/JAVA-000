package com.github.yooryan.remote.okhttp;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;


/**
 * @author linyunrui
 */
public enum OkHttpClientObject {
    CLIENT;

    private okhttp3.OkHttpClient clientInstance;

    private Integer connectTimeout_time = 10;
    private Integer writeTimeout_time = 10;
    private Integer readTimeout_time = 30;

    OkHttpClientObject() {
        clientInstance = new okhttp3.OkHttpClient.Builder()
                .connectTimeout(connectTimeout_time, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout_time, TimeUnit.SECONDS)
                .readTimeout(readTimeout_time, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    public okhttp3.OkHttpClient getClientInstance() {
        return clientInstance;
    }
}
