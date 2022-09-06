package com.ijhwang.user.dto;

import com.ijhwang.jwt.model.TeamInfo;
import com.ijhwang.jwt.model.UserInfo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TeamRequestDto {
	
	private String teamId;
	private String teamName;

	
	@Builder
	public TeamRequestDto(String teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
	}
	
	public TeamInfo toEntity() {
		return TeamInfo.builder()
				       .teamId(teamId)
				       .teamName(teamName)
				       .build();
	}
}
