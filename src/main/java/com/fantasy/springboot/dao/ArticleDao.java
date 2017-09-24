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

import com.fantasy.springboot.domain.Article;
import com.fantasy.springboot.domain.SearchParams;

@Repository
public class ArticleDao {
	
	public static final int LIMIT = 15;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(Article article) {
		mongoTemplate.save(article);
	}

	public Article findArticleById(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Article.class);
	}

	public void update(Article article) {
		Query query = new Query(Criteria.where("id").is(article.getId()));
		Update update = new Update().set("title", article.getTitle())
				.set("belong", article.getBelong())
				.set("content", article.getContent())
				.set("evaluate", article.getEvaluate())
				.set("updateTime", new Date().getTime());
		mongoTemplate.updateFirst(query, update, Article.class);
	}

	public void delete(String id) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Article.class);
	}

	public List<Article> findAll() {
		return mongoTemplate.findAll(Article.class);
	}
	
	public Object getArticlesByParams(SearchParams obj) {
		System.out.println("selectCate:"+obj.getSelectCate());
		System.out.println("keyword:"+obj.getKeyword());
		List<Article> articleList = null;
		long totalCount = countAllArticles(); //总记录数
        try {
            Query query = new Query();  
            if(obj.getSelectCate()!=null&&!"".equals(obj.getSelectCate())){
                query.addCriteria(Criteria.where("belong").is(obj.getSelectCate()));  
            }
            if(obj.getKeyword()!=null&&!"".equals(obj.getKeyword())){
                query.addCriteria(new Criteria("title").regex(".*?" + obj.getKeyword() + ".*"));  
            }

            query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updateTime"))).skip((obj.getCurrentPage()-1)*LIMIT).limit(LIMIT);
            articleList = mongoTemplate.find(query, Article.class); 
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        Map<String, Object> map = new HashMap<>();
        map.put("articleList", articleList);
        map.put("totalCount", totalCount);
        return map;  
	}
	
	//查询文章总数量
	public  long countAllArticles() {
		Query query = new Query();
        return mongoTemplate.count(query, Article.class);
	}
	
	//置顶文章
	public  void upArticle(String id,int isUp) {
		System.out.println("dao id:"+id);
		System.out.println("dao isUp:"+isUp);
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("isUp", isUp);
		mongoTemplate.updateFirst(query, update, Article.class);
	}
	
	//隐藏文章
	public  void hideArtcile(String id,int isHide) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("isHide", isHide);
		mongoTemplate.updateFirst(query, update, Article.class);
	}
}
