package com.ijhwang.jwt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter2 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("필터2");
		
		chain.doFilter(request, response); // 필터체인으로 등록 후 넘겨준다. => 다음 필터체인 타라고 넘겨줌, 만약 response.getWriter() out.print 등 writer 해버리면 끝나버린다.
		
	}
}
