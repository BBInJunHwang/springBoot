package com.ijhwang.common.domain;

import java.sql.Timestamp;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CommonEntity {
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp updateDate;
	
	private String createId;
	
	private String updateId;

}
