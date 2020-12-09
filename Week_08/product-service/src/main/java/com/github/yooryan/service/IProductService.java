package com.github.yooryan.service;

import com.github.yooryan.entity.Product;

/**
 * @author linyunrui
 */
public interface IProductService {

    /**
     * 购买商品
     * @param product
     */
    void doBuy(Product product);
}
