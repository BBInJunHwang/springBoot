package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//@Configuration // 빈 등록 -> 스프링 컨테이너에서 객체를 관리
//@EnableWebSecurity	// 시큐리티 필터 추가 -> 시큐리티 활성화 되어있는데, 어떤 설정을 해당파일로 하겠다, 시큐리티가 모든 req 가로챈다. controller 가기전에 필터링 필요
					// 시큐리티 필터 등록이 된다.
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 권한/인증 미리 체크한다.
												   // 어떤 요청시 요청 수행 후 시큐리티 시작이 아닌, 먼저 시큐리티가 체크한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.authorizeHttpRequests()    // req가 들어오면
			.antMatchers("/auth/**").permitAll()
			.anyRequest().authenticated();
	}

}
