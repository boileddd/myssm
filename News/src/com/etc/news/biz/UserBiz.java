package com.etc.news.biz;

import java.util.List;

import com.etc.news.entity.User;

public interface UserBiz {

	//����ҵ���߼���
	
	public User login(String name, String pwd) ;
	
	public List<User> queryAllUser();
}
