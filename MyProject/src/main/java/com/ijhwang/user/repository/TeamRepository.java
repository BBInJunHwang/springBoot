package com.ijhwang.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijhwang.jwt.model.TeamInfo;

public interface TeamRepository extends JpaRepository<TeamInfo, String> {
	
	public Optional<TeamInfo> findByTeamId(String teamId);

}
