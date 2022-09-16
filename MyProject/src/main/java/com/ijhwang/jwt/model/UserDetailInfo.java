package com.ijhwang.jwt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class UserDetailInfo extends CommonEntity{

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLY_SEQ_GEN") // 넘버링 전략, 해당 프로젝트에 연결된 DB의 넘버링 전략 따라감, 시퀀스를 사용하겠다는 의미
	private int id;
	
	private String userDetails;
	
	@ColumnDefault("'N'")
	@Column(length =1) 
	private String delYn;
	
	@OneToOne
	@JoinColumn(name="userId")
	private UserInfo userInfo;
}
