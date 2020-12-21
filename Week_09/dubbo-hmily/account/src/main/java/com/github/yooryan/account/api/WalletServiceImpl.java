package com.github.yooryan.account.api;

import com.github.yooryan.account.api.mapper.WalletFreezeMapper;
import com.github.yooryan.api.WalletDTO;
import com.github.yooryan.api.WalletFreeze;
import com.github.yooryan.api.WalletServiceApi;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author linyunrui
 */
@DubboService
public class WalletServiceImpl implements WalletServiceApi {

    @Autowired
    private WalletFreezeMapper walletFreezeMapper;

    @Override
  //  @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Object freeze(WalletDTO walletDTO) {
        final WalletFreeze walletFreeze = new WalletFreeze();
        BeanUtils.copyProperties(walletDTO,walletFreeze);
        walletFreeze.setBalance(walletDTO.getAmount());
        walletFreezeMapper.insert(walletFreeze);
        throw new RuntimeException("冻结失败");
      //  return "success";
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
