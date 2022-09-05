package com.ijhwang.jwt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter3 implements Filter{
	
	private static final String METHOD_POST = "POST";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("필터3");
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		chain.doFilter(request, response);
		
//		if(METHOD_POST.equals(req.getMethod())){
//			String headerAuth = req.getHeader("Authorization");
//			System.out.println(headerAuth);
//			
//			chain.doFilter(request, response);
//			
//			// 임시 테스트 토큰
////			if("tokenTest".equals(headerAuth)) {
////				chain.doFilter(request, response); // 필터체인으로 등록 후 넘겨준다. => 다음 필터체인 타라고 넘겨줌, 만약 response.getWriter() out.print 등 writer 해버리면 끝나버린다.
////			}else {
////				PrintWriter out = res.getWriter();
////				out.println("인증안됨");
////			}
//		}
	}

}
