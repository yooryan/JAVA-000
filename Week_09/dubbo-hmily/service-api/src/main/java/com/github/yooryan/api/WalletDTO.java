package com.github.yooryan.api;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author linyunrui
 */
public class WalletDTO implements Serializable {

    private Long id;

    private BigDecimal amount;

    /**
     * 1-美元账户冻结 2-人民币账户冻结
     */
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
