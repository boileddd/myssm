package com.etc.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.news.biz.NewsBiz;
import com.etc.news.biz.TypeBiz;
import com.etc.news.biz.UserBiz;
import com.etc.news.bizimpl.NewsBizImpl;
import com.etc.news.bizimpl.TypeBizImpl;
import com.etc.news.bizimpl.UserBizImpl;
import com.etc.news.entity.News;
import com.etc.news.entity.Type;
import com.etc.news.entity.User;

public class DoQueryNewsById2 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoQueryNewsById2() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 获取地址栏传递过来的值
		String newsid = request.getParameter("newsid");

		// 调用业务逻辑层 根据newsid 查询返回对应的新闻对象
		NewsBiz biz = new NewsBizImpl();

		// 查询对应的 新闻对象
		News news = biz.queryNewsById(Integer.valueOf(newsid));

		// 查询所有作者
		UserBiz biz2 = new UserBizImpl();
		List<User> list = biz2.queryAllUser();
		// 查询所有新闻类型
		TypeBiz biz3 = new TypeBizImpl();
		List<Type> list2 = biz3.queryAllType();
		// 将新闻对象, 所有用户,所有类型 放入请求域中

		request.setAttribute("news", news);
		request.setAttribute("userlist", list);
		request.setAttribute("typelist", list2);

		// 转发editNews.jsp
		request.getRequestDispatcher("editNews.jsp").forward(request, response);

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
