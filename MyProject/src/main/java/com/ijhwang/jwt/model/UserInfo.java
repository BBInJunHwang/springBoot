package com.ijhwang.jwt.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ijhwang.common.domain.CommonEntity;
import com.ijhwang.user.dto.UserRequestDto;

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
@SQLDelete(sql ="UPDATE USERINFO SET DELYN = 'Y' WHERE USERID = ?")
public class UserInfo extends CommonEntity{
	
	@Id
	@Column(nullable = false, length =30) 
	private String userId;
	
	@Column(nullable = false, length =200) 
	private String password;
	
	private String username;
	
	// DB는 ROLE 타입이 없기 때문에 해당 타입이 Stirng 알려준다
	@Enumerated(EnumType.STRING)
	private RoleType roleType; // type을 enum에  적용된 값만 강제된다. USERs 같이 오타방지
	
	@ColumnDefault("'N'")
	@Column(length =1) 
	private String delYn;
	
	@ManyToOne(fetch = FetchType.EAGER)
	//@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="teamId")
	private TeamInfo teamInfo;
	
	@Builder
	public UserInfo(String userId, String password, String username, RoleType roleType,TeamInfo teamInfo) {
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.roleType = roleType;
		this.teamInfo = teamInfo;
	}
	
	public void update(UserRequestDto requestDto) {
		this.password = requestDto.getPassword();
		this.username = requestDto.getUsername();
		this.roleType = requestDto.getRoleType();
		this.updateId = requestDto.getUserId();
	}
	
	public void delete() {
		this.delYn = "Y";
	}
	
	
	public List<RoleType> getRoleList(){
		if(null != roleType) {
			return Arrays.asList(roleType);
		}
		return new ArrayList<>();
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", password=" + password + ", username=" + username + ", roleType="
				+ roleType + ", delYn=" + delYn + ", teamInfo=" + teamInfo + "]";
	}
}
