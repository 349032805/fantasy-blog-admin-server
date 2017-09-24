package com.fantasy.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.springboot.domain.Belong;
import com.fantasy.springboot.service.BelongService;
import com.fantasy.springboot.service.GlobalParams;

@RestController
@RequestMapping("/api")
public class BelongController {
	
	@Autowired
	private BelongService belongService;
	

	@RequestMapping(value = "/addOrUpdateBelong", produces = "application/json", method = RequestMethod.POST)
	public Object addOrUpdateBelong(@RequestBody Belong belong) {
		Belong b = belongService.findBelongByCate(belong.getBelong());
		if(b == null){
			belongService.save(belong);
		}else{
			belongService.update(belong);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}
	
	@RequestMapping("/getBelong")
	public Belong getBelong(String cate) {
		return belongService.findBelongByCate(cate);
	}

}
