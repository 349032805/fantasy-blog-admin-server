package com.fantasy.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.springboot.domain.User;
import com.fantasy.springboot.service.GlobalParams;
import com.fantasy.springboot.service.SessionMap;
import com.fantasy.springboot.service.UserService;

/**
 * Created by fanjun on 2017/8/14.
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	protected Session session;
	
	@Autowired
	protected SessionMap sessionMap;

	@RequestMapping("/")
	public String index() {
		return "welcome fantasyBlog!";
	}

	@RequestMapping(value = "/api/login", produces = "application/json", method = RequestMethod.POST)
	public Object login(@RequestBody User user) {
		// 自己项目,就不注册了,用第一个登录的人作为管理员
		long userCount = userService.countUser();
		System.out.println("userCount:" + userCount);
		
		Map<String, Object> map = new HashMap<>();
		
		Boolean flag = false;
		
		if(userCount == 0){ 
			//如果用户表为空,那保存当前的用户名和密码
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			userService.save(user);
			flag = true;
		
		}else{
			User u = userService.findUserByUsername(user.getUsername());
			if (u == null) {
				map.put(GlobalParams.SUCCESS, false);
				map.put(GlobalParams.ERROR_MSG, "无此用户!");
			} else {
				String md5Password = DigestUtils.md5Hex(user.getPassword());
				if (md5Password.equals(u.getPassword())) {
					
					flag = true;
				} else {
					map.put(GlobalParams.SUCCESS, false);
					map.put(GlobalParams.ERROR_MSG, "用户名或密码错误!");
				}

			}
		}
		
		if(flag){
			String token = DigestUtils.md5Hex(user.getUsername());
			map.put("username", user.getUsername());
			map.put("token", token);
			map.put(GlobalParams.SUCCESS, true);
			
//			session.setIsLogin(true);
//			session.setToken(token);
			
			sessionMap.register(token);
			
		}

		return map;

	}
}
