package com.fantasy.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasy.springboot.dao.ArticleDao;
import com.fantasy.springboot.domain.Article;
import com.fantasy.springboot.domain.SearchParams;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	public void save(Article article) {
		articleDao.save(article);
	}

	public Article findArticleById(String id) {
		return articleDao.findArticleById(id);
	}

	public void delete(String id) {
		articleDao.delete(id);
	}

	public void update(Article song) {
		articleDao.update(song);
	}

	public List<Article> findAll() {
		return articleDao.findAll();
	}
	
	public Object getArticlesByParams(SearchParams obj) {
		return articleDao.getArticlesByParams(obj);
	}
	
	public void upArticle(String id,int isUp) {
		articleDao.upArticle(id,isUp);
	}
	
	public void hideArtcile(String id,int isHide) {
		articleDao.hideArtcile(id,isHide);
	}

}
