package com.github.yooryan.shardingspherejdbc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yooryan.shardingspherejdbc.entity.User;

/**
 * @author linyunrui
 */
public interface UserService extends IService<User> {

    void saveUser(User user);


    User selectOneUser(Long id);

}
