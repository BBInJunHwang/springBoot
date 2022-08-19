package com.ijhwang.jwt.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ijhwang.jwt.config.auth.PrincipalDetails;
import com.ijhwang.jwt.model.UserJwt;
import com.ijhwang.jwt.repository.UserRepository;

// 시큐리티가 Filter 가지고 있으며, 필터중에서 BasicAuthenticationFilter가 있음
// 권한, 인증 필요한 특정 주소를 요청 시 위 필터를 무조건 탄다
// 만약 권한, 인증이 필요한 주소가 아니면 필터를 안탄다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

	private UserRepository userRepository;
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
		
	}
	

	// 인증이나 권한 필요 주소 요청시 해당 필터를 탄다.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//super.doFilterInternal(request, response, chain); // 이게 있으면 응답을 2번함 지워야함
		
		System.out.println("권한이나 인증이 필요한 주소 요청됨");
		
		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader : " + jwtHeader);
		
		// jwt 확인 
		if(null == jwtHeader || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		// "Bearer " 값 제거
		String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
		
		String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("username").asString();
			
		// 서명이 정상적으로 되면
		if(null != username) {
			UserJwt userEntity = userRepository.findByUsername(username);
			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			
			// JwtAuthenticationFilter.class 내 
			// Authentication authentication = authenticationManager.authenticate(authenticationToken);
			// 이렇게 authentication 객체를 만드는거는 로그인 시 loadUserByUsername이 호출되며 객체를 만드는거고
			// 토큰인증시 아래와 같이 강제로 만들어준다
			// 임의로 인증객체를 만들어준다, pw값은 null로 넣어도 되는 이유는 이미 토큰에서 username이 유효한걸 검증했기 때문
			// JWT 토큰 서명을 통해서 정상이면 Authentication 객체를 만들어준다.
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,principalDetails.getAuthorities());
			
			// 시큐리티를 저장할 수 있는 세션공간에 authentication 값을 넣어준다
			// 강제로 시큐리티 세션에 인증객체를 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			chain.doFilter(request, response);
		}
		
		
	}
}
