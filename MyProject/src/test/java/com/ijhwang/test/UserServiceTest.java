package com.ijhwang.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ijhwang.jwt.model.UserInfo;
import com.ijhwang.user.dto.UserRequestDto;
import com.ijhwang.user.dto.UserResponseDto;
import com.ijhwang.user.repository.UserRepository;
import com.ijhwang.user.service.UserService;


@ExtendWith(MockitoExtension.class)
@DisplayName("UserService 테스트")
class UserServiceTest {
	
	//private UserRepository userRepository = Mockito.mock(UserRepository.class);
	
	@Mock
	private UserRepository userRepository;
    private UserService userService;
	
    @BeforeEach
    public void setUp(){
    	userService = new UserService(userRepository);
    }
    
    
	@Test
	void test() throws Exception {
		
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setUserId("ijhwang");
		
		//UserInfo  userInfo = userService.findUser(userRequestDto);
	}
}
