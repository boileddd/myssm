package com.etc.news.bizimpl;

import java.util.List;

import com.etc.news.biz.UserBiz;
import com.etc.news.dao.UserDao;
import com.etc.news.daoimpl.UserDaoImpl;
import com.etc.news.entity.User;

public class UserBizImpl implements UserBiz{
	
	private UserDao dao=new UserDaoImpl();

	@Override
	public User login(String name, String pwd) {
		
		if(name==null||pwd==null||name.equals("")||pwd.equals("")){
			return null;
		}else{
			return dao.login(name, pwd);
		}
	}

	@Override
	public List<User> queryAllUser() {
		// TODO Auto-generated method stub
		return dao.queryAllUser();
	}

}
