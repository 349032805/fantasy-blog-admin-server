package com.fantasy.springboot.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.fantasy.springboot.domain.Belong;

@Repository
public class BelongDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(Belong belong) {
		mongoTemplate.save(belong);
	}

	public Belong findBelongByCate(String cate) {
		return mongoTemplate.findOne(new Query(Criteria.where("belong").is(cate)), Belong.class);
	}
	
	public void update(Belong belong) {
		Query query = new Query(Criteria.where("belong").is(belong.getBelong()));
		Update update = new Update().set("describe", belong.getDescribe());
		mongoTemplate.updateFirst(query, update, Belong.class);
	}

}
