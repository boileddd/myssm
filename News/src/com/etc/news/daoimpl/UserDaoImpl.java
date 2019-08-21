package com.etc.news.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.news.dao.UserDao;
import com.etc.news.entity.User;
import com.etc.news.util.DBCon;
import com.etc.news.util.DBUtil;


public class UserDaoImpl implements UserDao{

	private DBUtil util=new DBUtil();
	private DBCon con=new DBCon();
	
	@Override
	public User login(String name, String pwd) {
	
		Connection con=util.getConn();
		 ResultSet rs=null;
		 PreparedStatement ps=null;
		User user=null;
		try {
			 ps=con.prepareStatement("select * from t_user where user_name=? and user_pwd=?");
		    ps.setString(1, name);
		    ps.setString(2, pwd);
		     rs=   ps.executeQuery();
		    if(rs!=null){ 
		    	while(rs.next()){
		    		user=new User();
		    		Integer user_id=rs.getInt("user_id");
		    		String user_name=rs.getString("user_name");
		    		String user_pwd=rs.getString("user_pwd");
		    		user.setUser_id(user_id);
		    		user.setUser_name(user_name);
		    		user.setUser_pwd(user_pwd);
		    	}	
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.close(rs, ps, con);
		}
		
		
		
		
		
		return user;
	}

	@Override
	public User queryByUserId(Integer userId) {
		User user=null;
	     ResultSet rs=	con.query("select * from t_user where user_id=?", userId);
		try {
			while(rs.next()){
				user=new User();
	    		Integer user_id=rs.getInt("user_id");
	    		String user_name=rs.getString("user_name");
	    		String user_pwd=rs.getString("user_pwd");
	    		user.setUser_id(user_id);
	    		user.setUser_name(user_name);
	    		user.setUser_pwd(user_pwd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			con.closeAll();
		}
		
		return user;
	}

	@Override
	public List<User> queryAllUser() {
		List<User> list=new ArrayList<User>();
	    ResultSet rs=	con.query("select * from t_user");
		try {
			while(rs.next()){
				User user=new User();
				Integer user_id=rs.getInt("user_id");
				String user_name=rs.getString("user_name");
				String user_pwd=rs.getString("user_pwd");
				user.setUser_id(user_id);
				user.setUser_name(user_name);
				user.setUser_pwd(user_pwd);
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			con.closeAll();
		}
		return list;
	}

}
