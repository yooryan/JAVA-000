package com.github.yooryan.router;

import java.util.List;

/**
 * @author linyunrui
 */
public interface HttpEndpointRouter {

    String route(List<String> endpoints);

}