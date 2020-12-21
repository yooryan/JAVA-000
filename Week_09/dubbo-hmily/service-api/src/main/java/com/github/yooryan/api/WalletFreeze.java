package com.github.yooryan.api;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author linyunrui
 */
public class WalletFreeze implements Serializable {
    private Long id;

    private BigDecimal balance;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
