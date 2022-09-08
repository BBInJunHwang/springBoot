package com.ijhwang.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ijhwang.jwt.model.UserInfo;
import com.ijhwang.user.dto.UserResponseDto;
import com.ijhwang.user.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // 기존 설정 DB 교체 안함
class UserSelectTest {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	@Rollback(false)
	void test() {
		
		UserInfo userInfo = userRepository.findByUserId("ijhwang6").orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다.");
		});
		
		System.out.println("eager vs lazy");
		
		UserResponseDto userResponseDto = new UserResponseDto(userInfo);
		System.out.println(userResponseDto);
		//System.out.println(userInfo);
	}

}
