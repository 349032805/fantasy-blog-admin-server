package com.fantasy.springboot.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.fantasy.springboot.domain.SearchParams;
import com.fantasy.springboot.domain.WebFile;

@Repository
public class WebFileDao {
	
	public static final int LIMIT = 15;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(WebFile webFile) {
		mongoTemplate.save(webFile);
	}
	
	public Map getFilesByParams(SearchParams obj) {

		List<WebFile> fileList = null;
		long totalCount = countAllFiles(); //总记录数
		
        Query query = new Query();  
        if(obj.getSelectCate()!=null&&!"".equals(obj.getSelectCate())){
            query.addCriteria(Criteria.where("fileType").is(obj.getSelectCate()));  
        }
        query.skip((obj.getCurrentPage()-1)*LIMIT).limit(LIMIT);
        fileList = mongoTemplate.find(query, WebFile.class); 
            

        Map<String, Object> map = new HashMap<>();
        map.put("fileList", fileList);
        map.put("totalCount", totalCount);
        return map;  
	}
	

	public  long countAllFiles() {
		Query query = new Query();
        return mongoTemplate.count(query, WebFile.class);
	}
	
	public void delete(String id) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)),WebFile.class);
	}
	
	public void updateUseById(String id,String use) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("use", use);
		mongoTemplate.updateFirst(query, update, WebFile.class);
	}

}
