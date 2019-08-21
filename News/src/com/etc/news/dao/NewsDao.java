package com.etc.news.dao;

import java.util.List;

import com.etc.news.entity.News;

public interface NewsDao {
	
	//查询所有新闻
	
	public List<News> queryAllNews();
	
	public List<News> queryNewsByPage(Integer page,Integer size);

	public int getNewsCount();
	
	public News queryNewsById(Integer newsId);
	
	public boolean deleteNewsById(Integer newsid);
	
	public boolean updateNewsById(News news);
	
	public List<News> queryNewsByType(String typename);
	
	public List<News> autoSearchNewsTitle(String key);
}
