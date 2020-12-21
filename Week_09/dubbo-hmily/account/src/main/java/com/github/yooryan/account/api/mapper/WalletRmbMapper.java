package com.github.yooryan.account.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yooryan.api.WalletRmb;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author linyunrui
 */
@Mapper
@Repository
public interface WalletRmbMapper  extends BaseMapper<WalletRmb> {
}
