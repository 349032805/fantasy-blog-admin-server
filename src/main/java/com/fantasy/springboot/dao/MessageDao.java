package com.fantasy.springboot.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.fantasy.springboot.domain.Message;
import com.fantasy.springboot.domain.SearchParams;

@Repository
public class MessageDao {
	
	public static final int LIMIT = 15;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(Message message) {
		mongoTemplate.save(message);
	}

	public void delete(String id) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)),Message.class);
	}
	
	public Object getMessagesByParams(SearchParams obj) {

		List<Message> messageList = null;
		long totalCount = countAllMessages(); //总记录数
		
        Query query = new Query();  
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createTime"))).skip((obj.getCurrentPage()-1)*LIMIT).limit(LIMIT);
        messageList = mongoTemplate.find(query, Message.class); 
            

        Map<String, Object> map = new HashMap<>();
        map.put("messageList", messageList);
        map.put("totalCount", totalCount);
        return map;  
	}
	
	public  long countAllMessages() {
		Query query = new Query();
        return mongoTemplate.count(query, Message.class);
	}
	
	public void updateResponse(String id,String response) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("response", response)
				.set("responseTime", new Date().getTime());
		mongoTemplate.updateFirst(query, update, Message.class);
	}
	
}
