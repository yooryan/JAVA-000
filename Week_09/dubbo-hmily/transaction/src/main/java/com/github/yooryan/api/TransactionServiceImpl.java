package com.github.yooryan.api;

import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author linyunrui
 */
@Service
public class TransactionServiceImpl implements TransactionService {


    @DubboReference(url = "dubbo://127.0.0.1:12345")
    private WalletServiceApi walletServiceApi;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void deal() {
        final WalletDTO walletDTO = new WalletDTO();
        walletDTO.setAmount(new BigDecimal(1));
        //   walletDTO.setId(1L);
        walletDTO.setType(1);

        final WalletDTO walletDTO1 = new WalletDTO();
        walletDTO1.setAmount(new BigDecimal(7));
        //   walletDTO1.setId(2L);
        walletDTO1.setType(2);
        walletServiceApi.freeze(walletDTO);
        walletServiceApi.freeze(walletDTO1);

    }

    public void confirm() {
        System.out.println("confirm......");
        //冻结成功 , 订单交易
    }

    public void cancel() {
        System.out.println("cancel......");
        //冻结失败 , 释放资源
    }

}
