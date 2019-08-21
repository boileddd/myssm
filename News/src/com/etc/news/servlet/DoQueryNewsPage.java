package com.etc.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.news.biz.NewsBiz;
import com.etc.news.bizimpl.NewsBizImpl;
import com.etc.news.entity.News;

public class DoQueryNewsPage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoQueryNewsPage() {
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

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		NewsBiz biz = new NewsBizImpl();
		int page = 1; // 默认当前页数
		int size = 5; // 每页条数
		int pageCount; // 总页数
		int count = biz.getNewsCount();
		pageCount = (count % size == 0) ? (count / size) : (count / size + 1);

		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.valueOf(request.getParameter("page"));
		}

		page = page <= 0 ? 1 : page;
		page = page > pageCount ? pageCount : page;

		List<Integer> pageCountList = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pageCountList.add(i);
		}

		// 分页查询所有新闻

		List<News> list = biz.queryAllNewsPage(page, size);

		// 将list保存到请求域中

		request.setAttribute("list", list);
		request.setAttribute("pageCountList", pageCountList);
		request.setAttribute("page", page); // 当前页
		request.setAttribute("pageCount", pageCount); // 总页数

		// 转发
		request.getRequestDispatcher("manager.jsp").forward(request, response);

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
