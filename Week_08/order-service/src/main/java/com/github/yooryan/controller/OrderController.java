package com.github.yooryan.controller;

import com.github.yooryan.api.order.OrderServiceApi;
import com.github.yooryan.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyunrui
 */
@RestController
public class OrderController implements OrderServiceApi {

    @Autowired
    private IOrderService orderService;
    /**
     * 提交订单
     * @return
     */
    @Override
    public String submitOrder() {
        orderService.submitOrder();
        //throw new RuntimeException("订单提交失败");
        return "success";
    }
}
