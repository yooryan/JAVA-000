package com.github.yooryan.primarysecondary;

import com.github.yooryan.primarysecondary.entity.User;
import com.github.yooryan.primarysecondary.service.UserService;
import com.github.yooryan.shardingspherejdbc.web.UserController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest(classes = PrimarySecondaryApplication.class)
class PrimarySecondaryApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
	}

	@Test
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

	@Test
	public void testSelect(){
		User user = userService.selectOneUser(4L);
		System.out.println(user.toString());
	}

	@Test
	public void testShardingsphere(){
		userController.testSelect();
	}
}
