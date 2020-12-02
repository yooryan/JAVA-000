package com.github.yooryan.primarysecondary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yooryan.primarysecondary.dbswitch.Secondary;
import com.github.yooryan.primarysecondary.entity.User;
import com.github.yooryan.primarysecondary.mapper.UserMapper;
import com.github.yooryan.primarysecondary.service.UserService;
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
    @Secondary
    public void saveUser(User user) {
        baseMapper.saveUser(user);
    }
}
