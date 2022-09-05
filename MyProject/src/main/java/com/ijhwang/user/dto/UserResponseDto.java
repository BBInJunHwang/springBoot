package com.ijhwang.user.dto;

import com.ijhwang.jwt.model.RoleType;
import com.ijhwang.jwt.model.TeamInfo;
import com.ijhwang.jwt.model.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {
	
	private String userId;
	private String password;
	private String username;
	private RoleType roleType;
	private TeamInfo teamInfo;
	
	public UserResponseDto(UserInfo userInfo) {
		this.userId = userInfo.getUserId();
		this.password = userInfo.getPassword();
		this.username = userInfo.getUsername();
		this.roleType = userInfo.getRoleType();
		this.teamInfo = userInfo.getTeamInfo();
	}
}
