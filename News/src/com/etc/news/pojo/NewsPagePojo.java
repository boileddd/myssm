package com.etc.news.pojo;

import java.util.List;

import com.etc.news.entity.News;

public class NewsPagePojo {
	private List<News> list;
	private Integer page;
	private Integer pageCount;
	public List<News> getList() {
		return list;
	}
	public void setList(List<News> list) {
		this.list = list;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
	
}
