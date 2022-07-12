package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name="REPLY_SEQ_GEN",
					sequenceName="REPLY_SEQ",
					initialValue=1,
					allocationSize=1)
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLY_SEQ_GEN") // 넘버링 전략, 해당 프로젝트에 연결된 DB의 넘버링 전략 따라감, 시퀀스를 사용하겠다는 의미
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne //(여러개 reply는 하나의 board에 존재가능)
	@JoinColumn(name="boardId")
	private Board board;   // Board 테이블을 알아서 찾는다
	
	@ManyToOne 
	@JoinColumn(name="userId")
	private UserTB user; // User 테이블을 알아서 찾는다
	
	@CreationTimestamp
	private Timestamp createDate;

}



/**
 * 연관 관계 주인 = FK를 가진 오브젝트
 * ex) board 상세보기할땐 ,user와 reply 정보가 같이 필요하다
 * 자바 -> JPA -> DB 조회시 
 * 
 * jpa 가 단순 select board가 아닌 board join user,reply 한상태로 쿼리실행한다.
 * reply는 1개가 아니라 List이다.
 * 
 * */




