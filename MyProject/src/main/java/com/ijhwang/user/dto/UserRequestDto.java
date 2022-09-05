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
	private TeamInfo teamInfo;

	
	@Builder
	public UserRequestDto(String userId, String password, String username, RoleType roleType,TeamInfo teamInfo) {
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.roleType = roleType;
		this.teamInfo = teamInfo;
	}
	
	//dto -> entity
	public UserInfo toEntity() {
		return UserInfo.builder()
				       .userId(userId)
				       .password(password)
				       .username(username)
				       .roleType(roleType)
				       .teamInfo(teamInfo)
				       .build();
	}
	
}
