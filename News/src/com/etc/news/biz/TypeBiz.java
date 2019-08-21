package com.etc.news.biz;

import java.util.List;

import com.etc.news.entity.Type;

public interface TypeBiz {
	
	public boolean addType(String typename);
	
	public List<Type> queryAllType();

}
