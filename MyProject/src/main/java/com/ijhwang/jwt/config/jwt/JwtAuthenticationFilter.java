package com.ijhwang.jwt.config.jwt;


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
import com.ijhwang.jwt.model.UserInfo;

import lombok.RequiredArgsConstructor;

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
			
			ObjectMapper mapper = new ObjectMapper();
			UserInfo userInfo = mapper.readValue(request.getInputStream(), UserInfo.class);
			System.out.println(userInfo);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo.getUserId(),userInfo.getPassword());
			
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.println(principalDetails.getUsername());

			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		System.out.println("successfulAuthentication 실행됨 -> 인증이 완료되었다는 뜻");
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		
		String jwtToken = JWT.create()
							.withSubject(principalDetails.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))  // 현재시간 +  a => 1000(1초), 60000(1분) 
							.withClaim("userId", principalDetails.getUserInfo().getUserId())
							.withClaim("username", principalDetails.getUserInfo().getUsername())
							.withClaim("roleType", principalDetails.getUserInfo().getRoleType().toString())
							.sign(Algorithm.HMAC512(JwtProperties.SECRET)); // 서버만 알고 있는 고유값으로 sign 
		
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
	}
}
