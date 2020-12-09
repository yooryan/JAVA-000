package com.github.yooryan.feign;

import com.github.yooryan.api.order.OrderServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author linyunrui
 */
@FeignClient(value = "order-service")
public interface OrderServiceFeignApi extends OrderServiceApi {

}
