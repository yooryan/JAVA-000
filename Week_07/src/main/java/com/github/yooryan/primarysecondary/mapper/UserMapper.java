package com.github.yooryan.primarysecondary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yooryan.primarysecondary.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author linyunrui
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void saveUser(User user);

    User selectOneUser(Long id);
}
