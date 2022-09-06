package com.ijhwang.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

import com.ijhwang.jwt.config.jwt.JwtAuthenticationFilter;
import com.ijhwang.jwt.config.jwt.JwtAuthorizationFilter;
import com.ijhwang.jwt.filter.MyFilter3;
import com.ijhwang.jwt.repository.JwtRepository;

import lombok.RequiredArgsConstructor;

@Configuration // ioc 등록
@EnableWebSecurity // 활성화
@RequiredArgsConstructor // DI
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final CorsFilter corsFilter;
	
	private final JwtRepository userRepository;
	
	
	@Bean // 해당 메서드의 리턴되는 오브젝트를 IOC로 등록해준다.
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//http.addFilter(new MyFilter1()); // 그냥 넣으면 security Filter에 등록이 안된다. securityFilter만 등록된다.
		//http.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class); // security 필터 이전/이후에 걸어야함 before/after
							//=> 하지만 굳이 시큐리티 필터 앞뒤에 걸필요는 없다. 따로 걸어주는 가이드 ㄱㄱ
		
		//http.addFilterBefore(new MyFilter3(), BasicAuthenticationFilter.class); // before / after 상관없이 시큐리티 필터 먼저 실행 후 커스텀 필터 실행함 3 > 1 > 2
		//http.addFilterAfter(new MyFilter3(), BasicAuthenticationFilter.class); // 
		
		http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class); // SecurityContextPersistenceFilter는 스프링 시큐리티 필터중 가장먼저 실행되며
																					   // 해당 필터보다 before로 실행 시 스프링 시큐리티 필터보다 먼저 실행한다.
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 세션 사용 x 
		.and()
		.addFilter(corsFilter) // 해당 필터를 태운다 -> cors 정책 벗어남, @CrossOrigin(인증 x) 인증 필요시 시큐리티 필터에 등록해야함
		.formLogin().disable() // form login 사용 x
		.httpBasic().disable()
		.addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager 파라미터 같이 줘야함 , WebSecurityConfigurerAdapter 가 authenticationManager 가지고 있기때문에 그대로 던져주면됨
		.addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))
		.authorizeRequests()
		.antMatchers("/api/v1/user/**")
		.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/api/v1/manager/**")
		.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/api/v1/admin/**")
		.access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll();

		// formLogin().disable로 인해서 "/login" 들어올시 로그인 처리가 불가능하다. 직접 로그인 처리 가능한 로직 구현해야함
		/*
		 * .and() .formLogin() .loginProcessingUrl("/login");
		 */
	}

}
