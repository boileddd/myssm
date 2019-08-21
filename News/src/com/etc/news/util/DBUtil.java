package com.etc.news.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * ���ݿ�����ĸ�����
 */
public class DBUtil {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/news";
	private static final String USER = "root"; // �û���
	private static final String PASSWORD = "495517";// ����

	/**
	 * ��ȡ���Ӷ���
	 * 
	 * @return ���Ӷ���
	 */
	public static Connection getConn() {

		Connection conn = null;
		try {

			Class.forName(DRIVER);
			// �õ����Ӷ���
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (Exception e) {
			throw new RuntimeException("���ݿ�����ʧ��!", e);
		}
		return conn;
	}

	/**
	 * �ͷ���Դ
	 * 
	 * @param rs
	 *            �����
	 * @param pstmt
	 *            ��������
	 * @param conn
	 *            ���Ӷ���
	 */
	public static void close(ResultSet rs, PreparedStatement pstmt,
			Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("�ͷ���Դʧ��!", e);
		}
	}

	/**
	 * ���ò���
	 * 
	 * @param sql
	 * @param conn
	 * @param pstmt
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	private static PreparedStatement setPstmt(String sql, Connection conn,
			PreparedStatement pstmt, Object... param) throws SQLException {
		   pstmt = conn.prepareStatement(sql);
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				pstmt.setObject(i + 1, param[i]);
			}
		}
		return pstmt;
	}

	/**
	 * ͨ�õ����ݿ�(��,ɾ,��)��������
	 * 
	 * @param sql
	 *            sql���
	 * @param param
	 *            sql������
	 * @return ��Ӱ������
	 */
	public static int execute(String sql, Object... param) {
		Connection conn = getConn();
		try {
			return execute(sql, conn, param);
		} finally {
			close(null, null, conn);
		}
	}

	/**
	 * ͨ�õ���ɾ�Ĳ���(�������)
	 * 
	 * @param sql
	 * @param conn
	 * @param param
	 * @return
	 */
	public static int execute(String sql, Connection conn, Object... param) {
		PreparedStatement pstmt = null;
		try {
			pstmt = setPstmt(sql, conn, pstmt, param);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			//������õõ��쳣��Ϣ
			e.printStackTrace();
			throw new RuntimeException("���ݿ����ʧ��!", e);
		} finally {
			close(null, pstmt, null);
		}
	}



}
