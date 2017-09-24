package com.fantasy.springboot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasy.springboot.dao.WebFileDao;
import com.fantasy.springboot.domain.SearchParams;
import com.fantasy.springboot.domain.WebFile;

@Service
public class WebFileService {
	
	@Autowired
	private WebFileDao webFileDao;

	public void save(WebFile webFile) {
		webFileDao.save(webFile);
	}
	
	public Map getFilesByParams(SearchParams obj) {
		return webFileDao.getFilesByParams(obj);
	}
	
	public void delete(String id) {
		webFileDao.delete(id);
	}
	
	public void updateUseById(String id,String use) {
		webFileDao.updateUseById(id,use);
	}
	
}
