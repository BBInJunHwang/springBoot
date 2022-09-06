package com.ijhwang.user.dto;

import com.ijhwang.jwt.model.UserInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {
	
	private String userId;
	private String username;
	private TeamResponseDto teamResponseDto;
	
	public UserResponseDto(UserInfo userInfo) {
		this.userId = userInfo.getUserId();
		this.username = userInfo.getUsername();
		this.teamResponseDto = TeamResponseDto.builder()
												.teamId(userInfo.getTeamInfo().getTeamId())
												.teamName(userInfo.getTeamInfo().getTeamName())
												.build();
	}
}
