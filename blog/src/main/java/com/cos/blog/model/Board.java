package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
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
@SequenceGenerator(name="BOARD_SEQ_GEN",
					sequenceName="BOARD_SEQ",
					initialValue=1,
					allocationSize=1)
public class Board {
	
	@Id //pk
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GEN") // 넘버링 전략 auto increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 사용 <html>태그가 섞여서 디자인됨
	
	@ColumnDefault("0") // int 형이니까 "" 안에 ''없이 그냥 숫자로 감싼다
	private int count; // 조회수
	
	// FetchType=EAGER -> board select 시 user는 1건이기떄문에 바로 가져오겠다는 의미 (무조건들고온다)
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One , 한명의 User는 여러개 board 작성 가능하다
	@JoinColumn(name="userId") // 실제 테이블생성시 필드명은 userId, 자동으로 FK가 생성된다. user 테이블의 id <-> userId 외리캐 적용됨
	private UserTB user; // DB는 object를 저장할 수 없다. Fk 자바는 object 저장가능
	
	// FetchType = LAZY 필요하면 찾는다. 반드시 x 
	// FetchType = EAGER 반드시 찾는다.
	@OneToMany(mappedBy = "board",fetch=FetchType.EAGER) // 하나의 board는 여러개 reply 가질 수 있다. 	//@JoinColumn(name="reply") replyId 필요없다. , mappedBy 연관관계의 주인이 아니다 (FK가 아니다), DB에 컬럼을 만들지 마라, 단순 조인을 해서 값을 얻기위해 쓰는 필드이다. mappedBy = board는 reply의 Board board의 필드 이름이다.
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;

}
