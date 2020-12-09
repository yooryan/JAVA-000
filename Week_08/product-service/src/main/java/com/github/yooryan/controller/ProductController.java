package com.github.yooryan.controller;

import com.github.yooryan.api.product.ProductServiceApi;
import com.github.yooryan.entity.Product;
import com.github.yooryan.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyunrui
 */
@RestController
public class ProductController implements ProductServiceApi {

    @Autowired
    private IProductService productService;

    /**
     * 购买商品
     * @return
     */
    @Override
    public String doBuy() {
        productService.doBuy(new Product());
        return null;
    }
}
