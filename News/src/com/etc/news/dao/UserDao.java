package com.etc.news.dao;

import java.util.List;

import com.etc.news.entity.User;

public interface UserDao {
	
	//һ���� t_user �йص�JDBC����������userdao
	
	//��¼����
	public User  login(String name,String pwd);
	
	public User queryByUserId(Integer userId);
	
    public List<User> queryAllUser();
}
