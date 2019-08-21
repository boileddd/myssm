package com.etc.news.util;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBCon {

	
	 //驱动类
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	 //数据库链接字符串
	public static final String URL = "jdbc:mysql://localhost:3306/news";
	// 用户名
	public static final String NAME = "root";
	// 密码
	public static final String PWD = "495517";

	// 数据库链接对象
	Connection con = null;
	// 预编译语句对象
	PreparedStatement ps = null;
	// 结果集对象
	ResultSet rs = null;


	
	

	/**
	 * 返回数据库链接对象
	 * 
	 * @return
	 */
	public Connection getCon() {
		try {
			Class.forName(DRIVER);
			con =DriverManager.getConnection(URL, NAME, PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	/**
	 * 关闭关闭
	 */
	public void closeAll() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 增删改通用方法
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int update(String sql, Object... obj) {
		int result = 0;
		con = getCon();
		try {
			ps = con.prepareStatement(sql);

			if (obj != null) {
				// 不等于空证明有传质
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);
				}
			}
			
			result=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return result;
	}
	/**
	 * 查询
	 * @param sql
	 * @param obj
	 * @return
	 */
	public ResultSet query(String sql,Object ... obj){
		try {
			con=getCon();
			ps = con.prepareStatement(sql);
			if (obj != null) {
				// 不等于空证明有传质
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);
				}
			}
			rs=ps.executeQuery();
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return rs;
	}
	
	
	public String ClobToString(Clob clob){
		String str="";
		try {
			if(clob!=null){
				str=clob.getSubString(1, (int)clob.length());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	

}
