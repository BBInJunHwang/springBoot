package com.ijhwang.common.domain;

import java.sql.Timestamp;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class CommonEntity {
	
	@CreationTimestamp // 시간 자동 입력
	protected Timestamp createDate;
	
	@UpdateTimestamp // 시간 자동 입력
	protected Timestamp updateDate;
	
	protected String createId;
	
	protected String updateId;

}
