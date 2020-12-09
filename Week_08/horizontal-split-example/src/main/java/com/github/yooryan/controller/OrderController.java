package com.github.yooryan.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yooryan.entity.Order;
import com.github.yooryan.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-12-06
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/order/{id}")
    public Order getOne(@PathVariable("id")Long id){
        return orderService.getById(id);
    }

    @GetMapping("/order/{id}/{userId}")
    public Order getOne(@PathVariable("id")Long id,@PathVariable("userId")Long userId){
        final LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper.eq(Order::getId,id);
        orderLambdaQueryWrapper.eq(Order::getUserId,userId);
        return orderService.getOne(orderLambdaQueryWrapper);
    }

    @PostMapping("/order")
    public String saveOrder(){
        final Order order = new Order();
        order.setOrderSn(UUID.randomUUID().toString());
        order.setUserId(IdWorker.getId());
        orderService.save(order);
        return "success";
    }

    @DeleteMapping("/order/{id}")
    public void delete(@PathVariable("id") Long id){
        orderService.removeById(id);
    }
}

