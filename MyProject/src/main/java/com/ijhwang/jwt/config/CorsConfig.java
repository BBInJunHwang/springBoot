package com.ijhwang.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration // 스프링에서 관리
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFileter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowCredentials(true); // 서버가 응답시 json을 자바스크립트에서 처리 할 수 있게 할지 설정 -> false 일 시 자바스크립트로 요청 시 응답이 오지 않음
		config.addAllowedOrigin("*"); // 모든 ip 응답 허용
		config.addAllowedHeader("*"); // 모든 header 응답 허용
		config.addAllowedMethod("*"); // 모든 get,post,patch,delete 허용
		
		source.registerCorsConfiguration("/api/**", config); // 해당요청에 대해서 config 설정을 먹인다.
		return new CorsFilter(source);
	}

}
