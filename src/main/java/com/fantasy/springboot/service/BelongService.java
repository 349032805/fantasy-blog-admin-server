package com.fantasy.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasy.springboot.dao.BelongDao;
import com.fantasy.springboot.domain.Belong;

@Service
public class BelongService {
	
	@Autowired
	private BelongDao belongDao;

	public void save(Belong belong) {
		belongDao.save(belong);
	}
	
	public void update(Belong belong) {
		belongDao.update(belong);
	}
	
	public Belong findBelongByCate(String cate) {
		return belongDao.findBelongByCate(cate);
	}

}
