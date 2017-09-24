package com.fantasy.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.fantasy.springboot.domain.WebConfig;



@Repository
public class WebConfigDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(WebConfig webConfig) {
		mongoTemplate.save(webConfig);
	}

	public WebConfig findOne() {
		return mongoTemplate.findOne(new Query().limit(1), WebConfig.class);
	}

	public void update(WebConfig webConfig) {
		Query query = new Query(Criteria.where("id").is(webConfig.getId()));
		Update update = new Update().set("mainDomainName", webConfig.getMainDomainName())
				.set("detailDomainName", webConfig.getDetailDomainName())
				.set("adminDomainName", webConfig.getAdminDomainName());
		mongoTemplate.updateFirst(query, update, WebConfig.class);
	}

}
