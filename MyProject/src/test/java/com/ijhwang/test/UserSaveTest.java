package com.ijhwang.test;

import java.util.HashSet;
import java.util.Set;

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
import com.ijhwang.user.dto.TeamRequestDto;
import com.ijhwang.user.dto.UserRequestDto;
import com.ijhwang.user.repository.TeamRepository;
import com.ijhwang.user.repository.UserRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // 기존 설정 DB 교체 안함
class UserSaveTest {
	
	Set<UserRequestDto> userRequestDtoList = new HashSet<>();
	UserRequestDto userRequestDto;
	TeamRequestDto teamRequestDto;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@BeforeEach
    public void setUp(){
		
		teamRequestDto = TeamRequestDto.builder()
									   .teamId("TEAM1")
									   .teamName("1번팀")
									   .build();
		
		//userRequestDtoList.add(userRequestDto);

		for(int i=0; i<10; i++) {
			String userId = "ijhwang" + String.valueOf(i);
			String userName = "황인준" + String.valueOf(i);
					
			userRequestDto = UserRequestDto.builder()
					   .userId(userId)
					   .password("$2a$10$DjZ4mQgviuCq0M8s2oumsOkQ3xcxd9ZO8fdFginvSX0TYCiDkykiO")
					   .username(userName)
					   .roleType(RoleType.ROLE_USER)
					   .teamRequestDto(teamRequestDto)
					   .build();
			userRequestDtoList.add(userRequestDto);
		}
    }
	
	
	@Test
	@Rollback(false)
	void test() {
		teamRepository.save(teamRequestDto.toEntity());
		userRequestDtoList.forEach(userRequestDto->{if(null != userRequestDto) {
			userRepository.save(userRequestDto.toEntity());
		}});
	}
}
