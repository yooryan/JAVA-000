package com.github.yooryan.api.order;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author linyunrui
 */
public interface OrderServiceApi {

    /**
     * 提交订单
     * @return
     */
    @Hmily
    @PostMapping("/order/submitOrder")
    String submitOrder();
}
