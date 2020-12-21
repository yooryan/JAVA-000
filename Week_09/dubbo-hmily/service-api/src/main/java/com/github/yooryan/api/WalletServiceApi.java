package com.github.yooryan.api;

import org.dromara.hmily.annotation.Hmily;

/**
 * @author linyunrui
 */
public interface WalletServiceApi {

    @Hmily
    Object freeze(WalletDTO walletDTO);
}
