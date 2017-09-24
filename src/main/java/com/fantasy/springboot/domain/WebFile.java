package com.fantasy.springboot.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "webFile")
public class WebFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String fileName;

	private String use; // 用途

	private String filePath; // 文件路径

	private String fileType; // 文件类型. 图片,文档,压缩,音频
	
	private String completePath;

	// private enum fileType {
	// image,document,zip,video
	// }

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompletePath() {
		return completePath;
	}

	public void setCompletePath(String completePath) {
		this.completePath = completePath;
	}
	
}
