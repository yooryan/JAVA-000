package com.github.yooryan.remote;

import com.github.yooryan.api.RpcfxRequest;
import com.github.yooryan.api.RpcfxResponse;

import java.io.IOException;

/**
 * @author linyunrui
 */
public interface RemoteClient {

    RpcfxResponse post(final RpcfxRequest req, final String url) throws IOException;
}
