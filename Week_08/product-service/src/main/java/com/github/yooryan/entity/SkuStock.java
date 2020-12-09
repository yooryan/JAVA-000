package com.github.yooryan.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-12-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SkuStock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 商品属性 ,json格式
     */
    private String spDate;


}
