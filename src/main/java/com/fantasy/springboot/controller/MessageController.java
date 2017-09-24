package com.fantasy.springboot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.springboot.domain.Message;
import com.fantasy.springboot.domain.SearchParams;
import com.fantasy.springboot.service.GlobalParams;
import com.fantasy.springboot.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	

	@RequestMapping(value = "/addMessage", produces = "application/json", method = RequestMethod.POST)
	public Object addMessage(@RequestBody Message message) {
		message.setCreateTime(new Date().getTime());
		messageService.save(message);
	
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}
	
	@RequestMapping(value = "/deleteMessage", produces = "application/json", method = RequestMethod.POST)
	public Object deleteMessage(String id) {
		messageService.delete(id);
		
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}
	
	@RequestMapping("/getMessagesByParams")
	public Object getMessagesByParams(SearchParams obj) {
		return messageService.getMessagesByParams(obj);
	}
	
	// 回复留言
	@RequestMapping(value = "/responseMessage", produces = "application/json", method = RequestMethod.POST)
	public Object responseMessage(String id,String response) {
		messageService.updateResponse(id,response);
		
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}

}
