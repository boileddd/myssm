package com.etc.news.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.news.dao.NewsDao;
import com.etc.news.dao.TypeDao;
import com.etc.news.dao.UserDao;
import com.etc.news.entity.News;
import com.etc.news.entity.Type;
import com.etc.news.entity.User;
import com.etc.news.util.DBCon;

public class NewsDaoImpl implements NewsDao{

	private DBCon con=new DBCon();
	private UserDao dao=new UserDaoImpl();
	private TypeDao typedao=new TypeDaoImpl();
	
	/**
	 * 用来查询所有新闻的方法
	 * @return 所有新闻的集合
	 * @author zyy
	 * @since 2019年7月8日14:36:02
	 */
	@Override
	public List<News> queryAllNews() {
	   List<News> list=new ArrayList<News>();
	   ResultSet rs=con.query("select * from t_news");
	    try {
			while(rs.next()){
				News news=new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setNews_title(rs.getString("news_title"));
				news.setNews_content(rs.getString("news_content"));
				news.setNews_image(rs.getString("news_image"));
				news.setNews_time(rs.getString("news_time"));
				
				Integer userid=rs.getInt("news_author");
				//根据 userid查询返回用户对象
				User user=dao.queryByUserId(userid);
				news.setNews_author(user);
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			con.closeAll();
		}
	   
		return list;
	}

	/**根据新闻id返回对应的新闻对象
	 * @param newsId  新闻id
	 * @return News  新闻对象
	 * @author zyy
	 * @since 2019年7月8日16:54:02
	 */
	@Override
	public News queryNewsById(Integer newsId) {
		News news=null;
	    ResultSet rs=	con.query("select * from t_news where news_id=?", newsId);
		try {
			if(rs.next()){
				news=new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setNews_title(rs.getString("news_title"));
				news.setNews_content(rs.getString("news_content"));
				news.setNews_image(rs.getString("news_image"));
				news.setNews_time(rs.getString("news_time"));
				
				//新闻类型
				Integer typeid=rs.getInt("news_type");
				//根据newsytype查询返回对应的新闻对象
				Type type=typedao.queryById(typeid);
				news.setNews_type(type);
				
				//新闻作者
				Integer userid=rs.getInt("news_author");
				//根据 userid查询返回用户对象
				User user=dao.queryByUserId(userid);
				news.setNews_author(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			con.closeAll();
		}
		
		
		return news;
	}

	@Override
	public boolean deleteNewsById(Integer newsid) {
		
		int i=con.update("delete from t_news where news_id=?", newsid);

		return i>0;
	}

	@Override
	public boolean updateNewsById(News news) {
		int i=0;
		if(news.getNews_image()==null||news.getNews_image().equals("")||news.getNews_image().equals("/null")){
			 i=con.update("update t_news set news_title=?,news_content=?,news_type=?,news_author=?,news_time=?  where news_id=?", news.getNews_title(),news.getNews_content(),news.getNews_type().getType_id(),news.getNews_author().getUser_id(),news.getNews_time(),news.getNews_id());	
		}else{
		     i=con.update("update t_news set news_title=?,news_content=?,news_type=?,news_author=?,news_time=?,news_image=?  where news_id=?", news.getNews_title(),news.getNews_content(),news.getNews_type().getType_id(),news.getNews_author().getUser_id(),news.getNews_time(),news.getNews_image(),news.getNews_id());
		}
		return i>0;
	}

	@Override
	public List<News> queryNewsByPage(Integer page, Integer size) {
		   List<News> list=new ArrayList<News>();
		   ResultSet rs=con.query("select * from t_news limit ?,? ",(page-1)*size,size);
		    try {
				while(rs.next()){
					News news=new News();
					news.setNews_id(rs.getInt("news_id"));
					news.setNews_title(rs.getString("news_title"));
					//新闻作者
					Integer userid=rs.getInt("news_author");
					//根据 userid查询返回用户对象
					User user=dao.queryByUserId(userid);
					news.setNews_author(user);
					news.setNews_time(rs.getString("news_time"));
					list.add(news);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				con.closeAll();
			}
		   
			return list;
	}

	@Override
	public int getNewsCount() {
	    int result=0;
		ResultSet rs=con.query("select count(*) as newscount from t_news");
		try {
			if(rs.next()){
				result=rs.getInt("newscount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			con.closeAll();
		}
		return result;
	}

	@Override
	public List<News> queryNewsByType(String typename) {
		List<News> list=new ArrayList<News>();
		   ResultSet rs=con.query("select news_id,news_title, news_author from t_news where news_type = (select type_id from t_type where type_name = ?)  ",typename);
		    try {
				while(rs.next()){
					News news=new News();
					news.setNews_id(rs.getInt("news_id"));
					news.setNews_title(rs.getString("news_title"));
					//新闻作者
					Integer userid=rs.getInt("news_author");
					//根据 userid查询返回用户对象
					User user=dao.queryByUserId(userid);
					news.setNews_author(user);
					list.add(news);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				con.closeAll();
			}
		   
			return list;
	}

	@Override
	public List<News> autoSearchNewsTitle(String key) {
		List<News> list=new ArrayList<News>();
		   ResultSet rs=con.query("select news_title from t_news where news_title like ? limit 0,5",key+"%");
		    try {
				while(rs.next()){
					News news=new News();
					news.setNews_title(rs.getString("news_title"));
					list.add(news);
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
