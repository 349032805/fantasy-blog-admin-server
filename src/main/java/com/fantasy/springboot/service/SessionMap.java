package com.fantasy.springboot.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SessionMap {

	private static Map<String, Date> map = new ConcurrentHashMap<>();

	public void register(String token) {
		map.put(token, new Date());
	}

	public boolean isLogin(String token) {
		if (StringUtils.isEmpty(token))
			return false;
		return map.containsKey(token);
	}
}
