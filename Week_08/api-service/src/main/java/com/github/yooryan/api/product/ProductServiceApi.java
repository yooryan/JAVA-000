package com.github.yooryan.api.product;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author linyunrui
 */
public interface ProductServiceApi {

    /**
     * 购买商品
     * @return
     */
    @PostMapping("/product/buy")
    String doBuy();
}
