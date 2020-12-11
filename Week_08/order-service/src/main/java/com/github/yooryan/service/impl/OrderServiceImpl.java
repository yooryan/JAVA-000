package com.github.yooryan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yooryan.entity.Order;
import com.github.yooryan.mapper.OrderMapper;
import com.github.yooryan.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-12-08
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void submitOrder() {
        final Order order = new Order();
        order.setUserId(IdWorker.getId());
        order.setOrderSn(UUID.randomUUID().toString());
        this.save(order);
        log.info("保存订单成功 ------");

    }

    public void confirm(){
        log.info("confirm.....");
    }

    public void cancel(){
        log.info("cancel.....");
    }
}
