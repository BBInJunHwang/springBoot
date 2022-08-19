package com.ijhwang.jwt.config.jwt;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ijhwang.jwt.config.auth.PrincipalDetails;
import com.ijhwang.jwt.model.UserJwt;

import lombok.RequiredArgsConstructor;

// 스프링 시큐리티에 UsernamePasswordAuthenticationFilter 가있으며, /login 등 로그인 프로세스 호출 시 
// POST로 username password 전송 시 필터가 동작한다.
// 지금 안하는 이유는 formLogin().disable로 인해 동작 안함
// JWT 에서 동작시키기 위해서는 해당 필터를 다시 securityConfig에 등록해준다.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;

	
	// "/login" 요청 시 로그인 시도를 위해서 실행되는 함수
	// UsernamePasswordAuthenticationFilter 가 낚아채서 해당 함수가 자동으로 실행된다.
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		System.out.println("Jwt AuthenticationFilter : 로그인 시도중");
		
		try {
			
			// 일반적으로 x-www-form-urlencoded를 쓰지만 최근에는 json 요청 (js, 안드로이드 등 )
			
			// json 데이터 parsing
			ObjectMapper mapper = new ObjectMapper();
			UserJwt user = mapper.readValue(request.getInputStream(), UserJwt.class);
			System.out.println(user);
			
			// 토큰을 만들어준다.
			// 임시 인증토큰임
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
			
			// 위에서 만든 토큰을 넘겨줘서 인증시킨다.
			// PrincipalsDetailsService 내 loadUserByUsername 함수가 실행됨 -> 이때 username만 받고, password 는 스프링에서 DB처리 알아서 해준다.
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			// authentication 안에는 로그인한 정보가 담김 -> 인증이 정상적으로 되어서 authentication 객체가 세션영역에 저장됨
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.println(principalDetails.getUsername());

			// id,pw 받아서 AuthenticationManager 로그인 시도 시 PrincipalDetailsService 가 호출 -> loadUserByUsername 실행
			// -> PrincipalDetails를 세션에 담고 -> JWT 토큰을 만들어 응답한다.
			// PrincipalDetails를 세션을 담는 이유는 권한관리를 위해서 (시큐리티에서는 값이 있어야 권한관리가 된다 hasRole.. 등)
			return authentication; // authentication 객체가 세션영역에 저장됨
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	// attemptAuthentication 실행 후 인증 정상적으로 되면 -> successfulAuthentication 함수가 실행된다.
	// 여기서 JWT 토큰 생성 후 토큰 response 해줌 
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("successfulAuthentication 실행됨 -> 인증이 완료되었다는 뜻");
		
		
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		
		// RSA 방식이 아닌 Hash 방식 
		String jwtToken = JWT.create()
							.withSubject(principalDetails.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))  // 현재시간 +  a => 1000(1초), 60000(1분) 
							.withClaim("id", principalDetails.getUser().getId())
							.withClaim("username", principalDetails.getUser().getUsername())
							.sign(Algorithm.HMAC512(JwtProperties.SECRET)); // 서버만 알고 있는 고유값으로 sign 
		
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
	}
}
