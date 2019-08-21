package com.etc.news.bizimpl;

import java.util.List;

import com.etc.news.biz.NewsBiz;
import com.etc.news.dao.NewsDao;
import com.etc.news.daoimpl.NewsDaoImpl;
import com.etc.news.entity.News;

public class NewsBizImpl implements NewsBiz{

	private NewsDao dao=new NewsDaoImpl();
	
	@Override
	public List<News> queryAllNews() {
		// TODO Auto-generated method stub
		return dao.queryAllNews();
	}

	@Override
	public News queryNewsById(Integer newsId) {
		if(newsId==null||newsId<=0){
			return null;
		}
		return dao.queryNewsById(newsId);
	}

	@Override
	public boolean deleteNewsById(Integer newsid) {
		if(newsid==null||newsid<=0){
			return false;
		}
		return dao.deleteNewsById(newsid);
	}

	@Override
	public boolean updateNewsById(News news) {
      if(news==null||news.getNews_id()==null||news.getNews_id()<=0){
    	 return false; 
      }

		return dao.updateNewsById(news);
	}

	@Override
	public List<News> queryAllNewsPage(Integer page, Integer size) {
		if(page<=0||size<=0){
			return null;
		}	
		return dao.queryNewsByPage(page, size);
	}

	@Override
	public int getNewsCount() {
		// TODO Auto-generated method stub
		return dao.getNewsCount();
	}

	@Override
	public List<News> queryNewsByType(String typename) {
		if(typename.equals("")||typename==null){
			return null;
		}
		return dao.queryNewsByType(typename);
	}

	@Override
	public List<News> autoSearchNewsTitle(String key) {
		// TODO Auto-generated method stub
		return dao.autoSearchNewsTitle(key);
	}

}
