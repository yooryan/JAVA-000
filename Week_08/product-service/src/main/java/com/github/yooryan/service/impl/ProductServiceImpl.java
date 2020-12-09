package com.github.yooryan.service.impl;

import com.github.yooryan.entity.Product;
import com.github.yooryan.feign.OrderServiceFeignApi;
import com.github.yooryan.service.IProductService;
import com.github.yooryan.service.ISkuStockService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-12-08
 */
@Slf4j
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ISkuStockService skuStockService;
    @Autowired
    private OrderServiceFeignApi orderServiceFeignApi;

    @Override
    @HmilyTCC(confirmMethod = "confirm",cancelMethod = "cancel")
    public void doBuy(Product product) {
        log.info("处理商品相关业务.....");
        log.info("锁定商品库存......");
        final int i = skuStockService.lockStock(1L);
        if (i == 1){
            log.info("库存锁定成功....");
            log.info("提交订单....");
            final String s = orderServiceFeignApi.submitOrder();
            if ("success".equals(s)){
                log.info("提交订单成功....");
            }else {
                log.info("提交订单失败....");
            }
        }else {
            log.info("库存锁定失败。。。库存不足");
        }
    }

    public void confirm(Product product){
        log.info("confirm.....");
    }

    public void cancel(Product product){
        log.info("cancel.....");
    }
}
