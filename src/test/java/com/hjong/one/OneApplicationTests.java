package com.hjong.one;

import com.hjong.one.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class OneApplicationTests {

	@Resource
	UserServiceImpl userService;
	@Test
	void contextLoads() {
		System.out.println(userService.findUserInfo(1));
	}

}
