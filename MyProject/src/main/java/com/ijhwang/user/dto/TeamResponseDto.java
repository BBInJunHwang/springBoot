package com.ijhwang.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class TeamResponseDto {
	
	private String teamId;
	private String teamName;
	
	@Builder
	public TeamResponseDto(String teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
	}
}
