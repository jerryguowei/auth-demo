package com.duduanan.authdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.duduanan.authdemo.entity.User;
import com.duduanan.authdemo.repository.UserRepository;

@SpringBootTest
class AuthDemoApplicationTests {
	
	@Autowired
	private UserRepository userPository;
	@Test
	void contextLoads() {
		User user = userPository.findByEmail("jerryguowei@gmail.com");
		
		System.out.println(user.getFirstName());
	}

}
