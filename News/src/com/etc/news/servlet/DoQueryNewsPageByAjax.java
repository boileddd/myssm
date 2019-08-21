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
import com.etc.news.pojo.NewsPagePojo;
import com.google.gson.Gson;

public class DoQueryNewsPageByAjax extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoQueryNewsPageByAjax() {
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

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		NewsBiz biz = new NewsBizImpl();
		int size = 5; // 每页显示五条
		int page = 1; // 默认第一次进来是第1页
		int pageCount = 0;// 总页数
		int count = biz.getNewsCount();
		// 总页数
		pageCount = (count % size == 0 ? (count / size) : (count / size + 1));

		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			try {
				page = Integer.valueOf(request.getParameter("page"));
			} catch (Exception e) {
				page = 1;
			}
			// 容错
			page = page <= 0 ? 1 : page;
			page = page > pageCount ? pageCount : page;
		}

		// 分页查询所有新闻
		List<News> list = biz.queryAllNewsPage(page, size);
		NewsPagePojo pojo = new NewsPagePojo();
		pojo.setList(list);
		pojo.setPage(page);
		pojo.setPageCount(pageCount);
		
		Gson gson = new Gson();
		String newslist = gson.toJson(pojo);

		out.print(newslist);
		out.flush();
		out.close();
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

		doGet(request, response);
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
