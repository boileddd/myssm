package com.etc.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.news.bizimpl.UserBizImpl;
import com.etc.news.entity.User;

public class DoMyLogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoMyLogin() {
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
		// 获取表单传递过来的值
		String username = request.getParameter("uname");
		String userpwd = request.getParameter("upwd");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// 调用业务逻辑层 (Spring)
		UserBizImpl biz = new UserBizImpl();
		User user = biz.login(username, userpwd);
		if (user == null) {
			// 登录失败回到登录页面

			out.print("<script>alert('用户名或者密码错误!!!');history.go(-1);</script>");
			// out.print("<script>alert('用户名或者密码错误!!!');location.href='index.jsp';</script>");
			// response.sendRedirect("index.jsp");
		} else {
			// 转发
			// 将要转发的值保存到请求域
			// request.setAttribute("user", user);
			// 如果数据需要每个页面共享,那么就将其存入session域
			session.setAttribute("user", user);

			request.getRequestDispatcher("DoQueryNewsPage").forward(request, response);
		}
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
