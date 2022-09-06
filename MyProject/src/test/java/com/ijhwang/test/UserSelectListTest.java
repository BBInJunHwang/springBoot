package com.ijhwang.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ijhwang.jwt.model.UserInfo;
import com.ijhwang.user.dto.UserResponseDto;
import com.ijhwang.user.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // 기존 설정 DB 교체 안함
class UserSelectListTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Rollback(false)
	void test() {
		
		List<UserResponseDto> userResponseDtoList = new ArrayList<>();
		List<UserInfo> userInfoList = userRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
		userInfoList.forEach(s->{if(null != s) {
			userResponseDtoList.add(new UserResponseDto(s));
		}});
		
		userResponseDtoList.forEach(s->{if(null != s) {
			System.out.println(s);
		}});
	}
}
