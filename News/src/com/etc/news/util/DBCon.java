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

	
	 //������
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	 //���ݿ������ַ���
	public static final String URL = "jdbc:mysql://localhost:3306/news";
	// �û���
	public static final String NAME = "root";
	// ����
	public static final String PWD = "495517";

	// ���ݿ����Ӷ���
	Connection con = null;
	// Ԥ����������
	PreparedStatement ps = null;
	// ���������
	ResultSet rs = null;


	
	

	/**
	 * �������ݿ����Ӷ���
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
	 * �رչر�
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
	 * ��ɾ��ͨ�÷���
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
				// �����ڿ�֤���д���
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
	 * ��ѯ
	 * @param sql
	 * @param obj
	 * @return
	 */
	public ResultSet query(String sql,Object ... obj){
		try {
			con=getCon();
			ps = con.prepareStatement(sql);
			if (obj != null) {
				// �����ڿ�֤���д���
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
