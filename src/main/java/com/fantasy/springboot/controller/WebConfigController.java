package com.fantasy.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.springboot.domain.WebConfig;
import com.fantasy.springboot.service.GlobalParams;
import com.fantasy.springboot.service.WebConfigService;

@RestController
@RequestMapping("/api")
public class WebConfigController {
	
	@Autowired
	private WebConfigService webConfigService;
	

	@RequestMapping(value = "/addOrUpdateConfig", produces = "application/json", method = RequestMethod.POST)
	public Object addOrUpdateConfig(@RequestBody WebConfig webconfig) {
		WebConfig wc = webConfigService.findOne();
		if(wc == null){
			webConfigService.save(webconfig);
		}else{
			webConfigService.update(webconfig);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}
	
	@RequestMapping("/getConfig")
	public WebConfig getConfig() {
		return webConfigService.findOne();
	}

}
