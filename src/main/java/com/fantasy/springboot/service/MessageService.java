package com.fantasy.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasy.springboot.dao.MessageDao;
import com.fantasy.springboot.domain.Message;
import com.fantasy.springboot.domain.SearchParams;

@Service
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;

	public void save(Message message) {
		messageDao.save(message);
	}
	
	public void delete(String id) {
		messageDao.delete(id);
	}
	
	public Object getMessagesByParams(SearchParams obj) {
		return messageDao.getMessagesByParams(obj);
	}
	
	public void updateResponse(String id, String response) {
		messageDao.updateResponse(id, response);
	}

}
