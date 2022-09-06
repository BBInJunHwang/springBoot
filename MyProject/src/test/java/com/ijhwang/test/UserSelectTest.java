package com.ijhwang.test;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
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
		System.out.println("end");
		
		UserResponseDto userResponseDto = new UserResponseDto(userInfo);
		System.out.println(userResponseDto);
		
		
		List<UserResponseDto> userResponseDtoList = new ArrayList<>();
		List<UserInfo> userInfoList = userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdate"));
		
		userInfoList.forEach(s->{if(null != s) {
			userResponseDtoList.add(new UserResponseDto(s));
		}});
		
		userResponseDtoList.forEach(s->{if(null != s) {
			System.out.println(s);
		}});
		
		
		
		//System.out.println(userInfo);
	}

}
