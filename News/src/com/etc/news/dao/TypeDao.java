package com.etc.news.dao;

import java.util.List;

import com.etc.news.entity.Type;

public interface TypeDao {
	
	public boolean addType(String typename);
	
	public Type queryById(Integer typeId);
	
	public List<Type> queryAllType();

}
