package com.github.yooryan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yooryan.entity.Order;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-12-08
 */
public interface IOrderService extends IService<Order> {

    void submitOrder();


}
