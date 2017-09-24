package com.fantasy.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fantasy.springboot.domain.SearchParams;
import com.fantasy.springboot.domain.WebFile;
import com.fantasy.springboot.service.FileStore;
import com.fantasy.springboot.service.GlobalParams;
import com.fantasy.springboot.service.WebFileService;

@RestController
@RequestMapping("/api")
public class WebFileController {

	@Autowired
	private FileStore fileStore;

	@Autowired
	private WebFileService webFileService;

	@RequestMapping(value = "/uploadWebFile", method = RequestMethod.POST)
	public Object uploadWebFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

		// 先保存文件,取到返回值,再将记录插入数据库
		Map map = fileStore.uploadBy(file);
		Map<String, Object> resultMap = new HashMap<>();

		if (map != null) {
			WebFile webFile = new WebFile();
			String fileType = (String) map.get("fileType");
			String filePath = (String) map.get("filePath");

			webFile.setFileName((String) map.get("fileName"));
			webFile.setFilePath(filePath);
			webFile.setFileType(fileType);
			webFileService.save(webFile);

			resultMap.put(GlobalParams.SUCCESS, true);

			if (fileType.equals("picture")) {
				String imageCompletePath = getFileBasePath(request) + filePath;
				resultMap.put("imageCompletePath", imageCompletePath);
			}

		} else {
			resultMap.put(GlobalParams.SUCCESS, false);
			resultMap.put(GlobalParams.ERROR_MSG, "文件已存在!");
		}

		return resultMap;

	}

	@RequestMapping("/getFilesByParams")
	public Map getFilesByParams(SearchParams obj,HttpServletRequest request) {
		Map map =  webFileService.getFilesByParams(obj);
		List<WebFile> newFileList = new ArrayList<>();
		List<WebFile> fileList = (List<WebFile>)map.get("fileList");
		for(WebFile wf : fileList){
			wf.setCompletePath(getFileBasePath(request)+wf.getFilePath());
			newFileList.add(wf);
		}
		map.put("newFileList", newFileList);
		return map;
	}

	@RequestMapping(value = "/deleteFile", produces = "application/json", method = RequestMethod.POST)
	public Object deleteFile(String id,String filePath) throws Exception {
		
		//先删除文件,再删除记录
		fileStore.deleteFileByPath(filePath);
		webFileService.delete(id);

		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}

	//修改用途
	@RequestMapping(value = "/updateUse", produces = "application/json", method = RequestMethod.POST)
	public Object updateUse(String id, String use) {

		webFileService.updateUseById(id, use);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}

	public String getFileBasePath(HttpServletRequest request) {
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		return basePath;
	}

}
