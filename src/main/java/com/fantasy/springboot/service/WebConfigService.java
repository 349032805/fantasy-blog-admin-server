package com.fantasy.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasy.springboot.dao.WebConfigDao;
import com.fantasy.springboot.domain.WebConfig;

@Service
public class WebConfigService {
	
	@Autowired
	private WebConfigDao webConfigDao;

	public void save(WebConfig webConfig) {
		webConfigDao.save(webConfig);
	}
	
	public void update(WebConfig webConfig) {
		webConfigDao.update(webConfig);
	}
	
	public WebConfig findOne() {
		return webConfigDao.findOne();
	}

}
