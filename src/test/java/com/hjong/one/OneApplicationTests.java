package com.hjong.one;

import com.hjong.one.util.key.KeyUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class OneApplicationTests {

	@Resource
	KeyUtil keyUtil;
	@Test
	void contextLoads() {
		System.out.println(keyUtil.generateKey());
	}

}
