package com.ijhwang.test;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ijhwang.jwt.model.RoleType;
import com.ijhwang.jwt.model.UserInfo;
import com.ijhwang.user.dto.UserRequestDto;
import com.ijhwang.user.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // 기존 설정 DB 교체 안함
class UserUpdateTest {

	UserRequestDto userRequestDto;

	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
    public void setUp(){
		userRequestDto = UserRequestDto.builder()
				   .userId("ijhwang1")
				   .password("55555555555")
				   .username("황인준1 수정5")
				   .roleType(RoleType.ROLE_ADMIN)
				   .build();
		}
	
	@Test
	@Rollback(false)
	void test() {
		
		Optional<UserInfo> userInfo = userRepository.findById(userRequestDto.getUserId());
		userInfo.ifPresent(selectUser ->{
			selectUser.update(userRequestDto);
		});
	}
}
