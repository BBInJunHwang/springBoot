package com.ijhwang.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ijhwang.user.dto.UserRequestDto;
import com.ijhwang.user.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // 기존 설정 DB 교체 안함
class UserDeleteTest {

	UserRequestDto userRequestDto;

	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
    public void setUp(){
		userRequestDto = UserRequestDto.builder()
				   .userId("ijhwang3")
				   .build();
		}
	
	@Test
	@Rollback(false)
	void test() {
		
//		Optional<UserInfo> userInfo = userRepository.findById(userRequestDto.getUserId());
//		userInfo.ifPresent(selectUser ->{
//			selectUser.delete();
//		});
		userRepository.deleteByUserId(userRequestDto.getUserId());
	}
}