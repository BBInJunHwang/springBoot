package com.cos.blog.model;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // getter/setter
@NoArgsConstructor // 빈생성자
@AllArgsConstructor // 전체생성자
@Builder // 빌더패턴
//ORM -> JAVA(다른언어) Object -> 테이블로 매핑해준다.
@Entity  // User 클래스가 서비스 실행시 mysql에 테이블 생성된다.
//@DynamicInsert // insert 시 null 필드 제외한다.
public class UserTB {
	
	@GeneratedValue(strategy = GenerationType.AUTO) // 넘버링 전략, 해당 프로젝트에 연결된 DB의 넘버링 전략 따라감, 시퀀스를 사용하겠다는 의미
	private int id; // 시퀀스; mysql auto increment
	
	@Id // pk 지정
	@Column(nullable = false, length =30) // not null, size 30
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault(" 'user' ") // default 값 ""안에 ''감싸서 문자로 인식해줘야함
//	private String role; // Enum 쓰는게 좋다. -> role에 대한 범위를 줄수있도록 도메인적용이 가능하다.
	
	// DB는 ROLE 타입이 없기 때문에 해당 타입이 Stirng 알려준다
	@Enumerated(EnumType.STRING)
	private RoleType role; // type을 enum에  적용된 값만 강제된다. USERs 같이 오타방지
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;

}
