package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@Getter  getter
//@Setter  setter
//@Data // getter setter 모두 만든다
// @AllArgsConstructor // 생성자 근데 요즘 트랜드 아님?

/**
 * 요즘 트랜드는 Member은 DB에셔 들고오고 불변이기 때문에 final 붙인다
 * 물론 가져온뒤 커스텀이 필요하면 final 제거
 * */
//@RequiredArgsConstructor // final 붙은 애들에 대한 생성자 생성


@Data
//@AllArgsConstructor // 필드 전체 생성자
@NoArgsConstructor // 빈생성자
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	

	
}
