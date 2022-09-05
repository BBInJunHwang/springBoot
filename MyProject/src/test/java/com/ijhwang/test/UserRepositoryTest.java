package com.ijhwang.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ijhwang.jwt.model.UserInfo;
import com.ijhwang.user.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // 기존 설정 DB 교체 안함
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	void test() {
		
		UserInfo userInfo = userRepository.findByUserId("ijhwang");
		System.out.println(userInfo);
	}

}
