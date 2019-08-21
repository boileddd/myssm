package com.etc.news.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.news.dao.TypeDao;
import com.etc.news.entity.Type;
import com.etc.news.util.DBCon;

public class TypeDaoImpl implements TypeDao{
	
	private DBCon con=new DBCon();

	@Override
	public boolean addType(String typename) {
	
	int i=con.update("insert into t_type(type_name) values(?)", typename);
	
		return i>0;
	}

	@Override
	public Type queryById(Integer typeId) {
		Type type=null;
		ResultSet rs=con.query("select * from t_type where type_id=?", typeId);
		try {
			if(rs.next()){
				type=new Type();
				type.setType_id(rs.getInt("type_id"));
				type.setType_name(rs.getString("type_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			con.closeAll();
		}
		
		return type;
	}

	@Override
	public List<Type> queryAllType() {
		List<Type> list=new ArrayList<Type>();
		ResultSet rs=con.query("select * from t_type");
		try {
			while(rs.next()){
				Type type=new Type();
				type.setType_id(rs.getInt("type_id"));
				type.setType_name(rs.getString("type_name"));
				list.add(type);
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
