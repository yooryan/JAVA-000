package com.github.yooryan.shardingspherejdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yooryan.shardingspherejdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author linyunrui
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void saveUser(User user);

    User selectOneUser(Long id);
}
