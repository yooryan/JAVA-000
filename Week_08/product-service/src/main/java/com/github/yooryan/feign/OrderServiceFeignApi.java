package com.github.yooryan.feign;

import com.github.yooryan.api.order.OrderServiceApi;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author linyunrui
 */
@FeignClient(value = "order-service")
public interface OrderServiceFeignApi  {
    /**
     * 提交订单
     * @return
     */
    @Hmily
    @PostMapping("/order/submitOrder")
    String submitOrder();

}
