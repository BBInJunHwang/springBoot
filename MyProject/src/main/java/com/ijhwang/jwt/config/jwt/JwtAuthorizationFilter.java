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
import com.ijhwang.jwt.model.UserInfo;
import com.ijhwang.jwt.repository.JwtRepository;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

	private JwtRepository userRepository;
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("권한이나 인증이 필요한 주소 요청됨");
		
		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader : " + jwtHeader);
		
		if(null == jwtHeader || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
		
		String userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("userId").asString();
			
		if(null != userId) {
			UserInfo userEntity = userRepository.findByUserId(userId);
			
			System.out.println(userEntity.toString());
			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,principalDetails.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			chain.doFilter(request, response);
		}
	}
}
