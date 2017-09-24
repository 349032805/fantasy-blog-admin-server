package com.fantasy.springboot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.springboot.domain.Article;
import com.fantasy.springboot.domain.SearchParams;
import com.fantasy.springboot.service.ArticleService;
import com.fantasy.springboot.service.GlobalParams;

@RestController
@RequestMapping("/api")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	

	@RequestMapping("/getArticleDetail")
	public Article getArticleDetail(String id) {
		return articleService.findArticleById(id);
	}

	@RequestMapping(value = "/deleteArticle", produces = "application/json", method = RequestMethod.POST)
	public Object deleteArticle(String id) {
		articleService.delete(id);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}

	@RequestMapping("/getAllArticles")
	public List<Article> getAllArticles() {
		return articleService.findAll();
	}

	@RequestMapping(value = "/updateArticle", produces = "application/json", method = RequestMethod.POST)
	public Object updateArticle(@RequestBody Article article) {
		articleService.update(article);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}

	@RequestMapping(value = "/addArticle", produces = "application/json", method = RequestMethod.POST)
	public Object addArticle(@RequestBody Article article) {
		article.setCreateTime(new Date().getTime());
		article.setUpdateTime(new Date().getTime());
		articleService.save(article);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}
	
	//根据参数获取文章列表
	@RequestMapping("/getArticlesByParams")
	public Object getArticlesByParams(SearchParams obj) {
		return articleService.getArticlesByParams(obj);
	}
	
	// 文章置顶
	@RequestMapping(value = "/setArticleUp", produces = "application/json", method = RequestMethod.POST)
	public Object setArticleUp(String id,int isUp) {
		System.out.println("upId:"+id);
		System.out.println("isUp:"+isUp);
		
		articleService.upArticle(id,isUp);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}
	
	//隐藏显示
	@RequestMapping(value = "/setArticleHide", produces = "application/json", method = RequestMethod.POST)
	public Object setArticleHide(String id,int isHide) {
		System.out.println("upId:"+id);
		System.out.println("isHide:"+isHide);
		
		articleService.hideArtcile(id,isHide);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}

}
