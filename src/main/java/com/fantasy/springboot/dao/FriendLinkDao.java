package com.fantasy.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.fantasy.springboot.domain.FriendLink;

@Repository
public class FriendLinkDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(FriendLink friendLink) {
		mongoTemplate.save(friendLink);
	}

	public void update(FriendLink friendLink) {
		Query query = new Query(Criteria.where("id").is(friendLink.getId()));
		Update update = new Update().set("sign", friendLink.getSign())
				.set("link", friendLink.getLink())
				.set("intro", friendLink.getIntro());
		mongoTemplate.updateFirst(query, update, FriendLink.class);
	}

	public void delete(String id) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)), FriendLink.class);
	}

	public List<FriendLink> findAll() {
		return mongoTemplate.findAll(FriendLink.class);
	}

}
