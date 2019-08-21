package com.etc.news.biz;

import java.util.List;

import com.etc.news.entity.News;

public interface NewsBiz {

	public int getNewsCount() ;
	
	public List<News> queryAllNews();
	
	public News queryNewsById(Integer newsId);
	
	public boolean deleteNewsById(Integer newsid);
	
	public boolean updateNewsById(News news);
	
	public List<News> queryAllNewsPage(Integer page,Integer size);
	
	public List<News> queryNewsByType(String typename);
	
	public List<News> autoSearchNewsTitle(String key);
}
