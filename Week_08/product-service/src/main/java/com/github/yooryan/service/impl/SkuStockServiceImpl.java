package com.github.yooryan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yooryan.entity.SkuStock;
import com.github.yooryan.mapper.SkuStockMapper;
import com.github.yooryan.service.ISkuStockService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-12-08
 */
@Service
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements ISkuStockService {

    @Override
    public int lockStock(Long productId) {
        return baseMapper.lockStock(productId);
    }
}
