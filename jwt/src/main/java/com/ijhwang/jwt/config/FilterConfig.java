package com.ijhwang.jwt.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ijhwang.jwt.filter.MyFilter1;
import com.ijhwang.jwt.filter.MyFilter2;

@Configuration // ioc 등록
public class FilterConfig {

	@Bean // request 가 요청올때 실행된다.
	public FilterRegistrationBean<MyFilter1> filter1(){
		FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
		bean.addUrlPatterns("/*"); // 모든 요청에 걸려라
		bean.setOrder(0); // 0이면 가장먼저 실행되는 필터 0 > 1 > 2...
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<MyFilter2> filter2(){
		FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
		bean.addUrlPatterns("/*"); // 모든 요청에 걸려라
		bean.setOrder(0); // 0이면 가장먼저 실행되는 필터 0 > 1 > 2...
		return bean;
	}
}
