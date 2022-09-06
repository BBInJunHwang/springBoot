package com.ijhwang.user.dto;

import com.ijhwang.jwt.model.RoleType;
import com.ijhwang.jwt.model.TeamInfo;
import com.ijhwang.jwt.model.UserInfo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequestDto {
	
	private String userId;
	private String password;
	private String username;
	private RoleType roleType;
	private TeamRequestDto teamRequestDto;

	
	@Builder
	public UserRequestDto(String userId, String password, String username, RoleType roleType,TeamRequestDto teamRequestDto) {
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.roleType = roleType;
		this.teamRequestDto = teamRequestDto;
	}
	
	//dto -> entity
	public UserInfo toEntity() {
		return UserInfo.builder()
				       .userId(userId)
				       .password(password)
				       .username(username)
				       .roleType(roleType)
				       .teamInfo(TeamInfo.builder().teamId(teamRequestDto.getTeamId()).teamName(teamRequestDto.getTeamName()).build())
				       .build();
	}
}
