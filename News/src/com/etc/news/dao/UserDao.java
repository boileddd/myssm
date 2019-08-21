package com.etc.news.dao;

import java.util.List;

import com.etc.news.entity.User;

public interface UserDao {
	
	//一切与 t_user 有关的JDBC操作都属于userdao
	
	//登录方法
	public User  login(String name,String pwd);
	
	public User queryByUserId(Integer userId);
	
    public List<User> queryAllUser();
}
