package com.fantasy.springboot.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "webConfig")
public class WebConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String mainDomainName; // 主站域名
	
	private String detailDomainName; //详情页面的域名
	
	private String adminDomainName; // 管理系统域名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetailDomainName() {
		return detailDomainName;
	}

	public void setDetailDomainName(String detailDomainName) {
		this.detailDomainName = detailDomainName;
	}

	public String getMainDomainName() {
		return mainDomainName;
	}

	public void setMainDomainName(String mainDomainName) {
		this.mainDomainName = mainDomainName;
	}

	public String getAdminDomainName() {
		return adminDomainName;
	}

	public void setAdminDomainName(String adminDomainName) {
		this.adminDomainName = adminDomainName;
	}
	

}
