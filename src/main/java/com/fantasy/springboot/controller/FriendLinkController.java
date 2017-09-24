package com.fantasy.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.springboot.domain.FriendLink;
import com.fantasy.springboot.service.FriendLinkService;
import com.fantasy.springboot.service.GlobalParams;

@RestController
@RequestMapping("/api")
public class FriendLinkController {
	
	@Autowired
	private FriendLinkService friendLinkService;
	

	@RequestMapping(value = "/deleteLink", produces = "application/json", method = RequestMethod.POST)
	public Object deleteLink(String id) {
		friendLinkService.delete(id);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}

	@RequestMapping("/getAllFriendLinks")
	public List<FriendLink> getAllFriendLinks() {
		return friendLinkService.findAll();
	}

	@RequestMapping(value = "/updateLink", produces = "application/json", method = RequestMethod.POST)
	public Object updateLink(@RequestBody FriendLink friendLink) {
		friendLinkService.update(friendLink);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}

	@RequestMapping(value = "/addLink", produces = "application/json", method = RequestMethod.POST)
	public Object addLink(@RequestBody FriendLink friendLink) {

		friendLinkService.save(friendLink);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}

}
