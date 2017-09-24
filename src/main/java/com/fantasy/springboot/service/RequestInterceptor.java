package com.fantasy.springboot.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fantasy.springboot.exception.UnauthorizedException;

@Service
public class RequestInterceptor extends HandlerInterceptorAdapter {

	// @Autowired
	// protected Session session;

	@Autowired
	protected SessionMap sessionMap;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("----------------------进入拦截器-----------------");
		String clientToken = getClientToken(request);
		System.out.println("客户端token:" + clientToken);

		System.out.println("sessionMap.isLogin():" + sessionMap.isLogin(clientToken));

		if (!sessionMap.isLogin(clientToken)) {
			throw new UnauthorizedException();
		}

		return super.preHandle(request, response, handler);
	}

	private String getClientToken(HttpServletRequest request) {
		String clientToken = "";
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			if (key.equals("token")) {
				clientToken = value;
			}
		}
		return clientToken;
	}
}
