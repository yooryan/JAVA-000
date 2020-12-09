package com.github.yooryan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author jobob
 * @since 2020-12-08
 */
@Data
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 店铺id
     */
    private Long storeId;

    /**
     * 物流信息id
     */
    private Long logisticsInfoId;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 支付平台 0-微信 1-支付宝
     */
    private Boolean payType;

    /**
     * 支付订单号
     */
    private String paymentOrderSn;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 订单状态 0-未支付 1-已支付 2-已发货 3-已签收 4-已完成 5-失效
     */
    private Integer status;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 确认收货状态 0-未确认 1-已确认
     */
    private Boolean confirmStatus;


}
