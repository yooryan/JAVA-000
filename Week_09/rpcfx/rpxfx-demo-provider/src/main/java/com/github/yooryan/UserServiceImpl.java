package com.github.yooryan;


import com.github.yooryan.api.User;
import com.github.yooryan.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
