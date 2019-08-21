package com.etc.news.biz;

import java.util.List;

import com.etc.news.entity.User;

public interface UserBiz {

	//处理业务逻辑层
	
	public User login(String name, String pwd) ;
	
	public List<User> queryAllUser();
}
