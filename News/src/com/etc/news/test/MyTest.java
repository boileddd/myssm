package com.etc.news.test;

import java.sql.Connection;

import com.etc.news.util.DBUtil;

public class MyTest {
	
	public static void main(String[] args) {
		
		DBUtil util=new DBUtil();
		Connection con= util.getConn();
		System.out.println(con);
		
	}

}
