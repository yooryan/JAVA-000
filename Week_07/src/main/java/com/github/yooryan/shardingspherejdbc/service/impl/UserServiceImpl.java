package com.github.yooryan.shardingspherejdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yooryan.shardingspherejdbc.entity.User;
import com.github.yooryan.shardingspherejdbc.mapper.UserMapper;
import com.github.yooryan.shardingspherejdbc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author linyunrui
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectOneUser(Long id) {
        return baseMapper.selectOneUser(id);
    }

    @Override
    public void saveUser(User user) {
        baseMapper.saveUser(user);
    }
}
