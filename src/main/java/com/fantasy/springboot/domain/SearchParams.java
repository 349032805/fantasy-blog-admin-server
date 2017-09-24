package com.fantasy.springboot.domain;

import java.io.Serializable;

public class SearchParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String selectCate;

	private String keyword;

	private int currentPage;

	public String getSelectCate() {
		return selectCate;
	}

	public void setSelectCate(String selectCate) {
		this.selectCate = selectCate;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
