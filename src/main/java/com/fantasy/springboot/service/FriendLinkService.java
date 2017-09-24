package com.fantasy.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasy.springboot.dao.FriendLinkDao;
import com.fantasy.springboot.domain.FriendLink;

@Service
public class FriendLinkService {
	
	@Autowired
	private FriendLinkDao friendLinkDao;

	public void save(FriendLink friendLink) {
		friendLinkDao.save(friendLink);
	}

	public void delete(String id) {
		friendLinkDao.delete(id);
	}

	public void update(FriendLink friendLink) {
		friendLinkDao.update(friendLink);
	}

	public List<FriendLink> findAll() {
		return friendLinkDao.findAll();
	}

}
