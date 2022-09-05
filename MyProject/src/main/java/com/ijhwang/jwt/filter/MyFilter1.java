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

public class MyFilter1 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("필터1");
		
		chain.doFilter(request, response); // 필터체인으로 등록 후 넘겨준다. => 다음 필터체인 타라고 넘겨줌, 만약 response.getWriter() out.print 등 writer 해버리면 끝나버린다.
	}
}
