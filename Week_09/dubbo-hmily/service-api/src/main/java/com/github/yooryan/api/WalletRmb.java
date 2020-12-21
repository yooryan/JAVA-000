package com.github.yooryan.api;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author linyunrui
 */
public class WalletRmb implements Serializable {

    private Long id;

    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
