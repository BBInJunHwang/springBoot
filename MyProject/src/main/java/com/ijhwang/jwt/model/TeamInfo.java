package com.ijhwang.jwt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.ijhwang.common.domain.CommonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor 
@Builder 
@Entity 
@Getter
@DynamicInsert
@DynamicUpdate
@Where(clause = "delYn = 'N'")
public class TeamInfo extends CommonEntity{
	
	@Id
	@Column(nullable = false, length =30) 
	private String teamId;
	
	@Column(nullable = false, length =50) 
	private String teamName;
	
	@ColumnDefault("'N'")
	@Column(length =1) 
	private String delYn;

	@Builder
	public TeamInfo(String teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
	}
	
	
	@Override
	public String toString() {
		return "TeamInfo [teamId=" + teamId + ", teamName=" + teamName + ", delYn=" + delYn + "]";
	}
}
