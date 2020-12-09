package com.github.yooryan.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yooryan.entity.SkuStock;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-12-08
 */
public interface ISkuStockService extends IService<SkuStock> {

    int lockStock(Long productId);
}
