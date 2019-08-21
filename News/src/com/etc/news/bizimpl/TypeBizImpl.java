package com.etc.news.bizimpl;

import java.util.List;

import com.etc.news.biz.TypeBiz;
import com.etc.news.dao.TypeDao;
import com.etc.news.daoimpl.TypeDaoImpl;
import com.etc.news.entity.Type;

public class TypeBizImpl implements TypeBiz{
	
	private TypeDao dao=new TypeDaoImpl();

	/**
	 * ����������͵ķ���
	 * @param typename  ��������
	 * @return boolean����  �Ƿ���ӳɹ�
	 * @author zyy
	 * @since 2019��7��8��16:13:31
	 */
	@Override
	public boolean addType(String typename) {
		if(typename==null||typename.equals("")){
			return false;
		}
		return dao.addType(typename);
	}

	@Override
	public List<Type> queryAllType() {
		// TODO Auto-generated method stub
		return dao.queryAllType();
	}

}
