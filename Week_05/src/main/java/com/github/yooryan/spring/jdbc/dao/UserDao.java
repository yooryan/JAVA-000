package com.github.yooryan.spring.jdbc.dao;

import com.github.yooryan.spring.jdbc.entity.User;

/**
 * @author linyunrui
 */
public interface UserDao {

    void save(User user);

    void update(User user);

    User selectOneById(Long id);

    void deleteById(Long id);
}
