package com.github.yooryan.primarysecondary.web;

import com.github.yooryan.primarysecondary.entity.User;
import com.github.yooryan.primarysecondary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author linyunrui
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("add")
    public void testAdd(){
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setGender(1);
        user.setUsername("123123123");
        user.setNickname("222");
        user.setPassword("2312312");
        user.setRegisterTime(LocalDateTime.now());
        user.setPhone("123123");
        userService.saveUser(user);
    }

    @GetMapping("select")
    public void testSelect(){
        User user = userService.selectOneUser(1L);
        System.out.println(user.toString());
    }
}
