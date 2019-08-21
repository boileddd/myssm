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
		// ��ȡ�����ݹ�����ֵ
		String username = request.getParameter("uname");
		String userpwd = request.getParameter("upwd");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// ����ҵ���߼��� (Spring)
		UserBizImpl biz = new UserBizImpl();
		User user = biz.login(username, userpwd);
		if (user == null) {
			// ��¼ʧ�ܻص���¼ҳ��

			out.print("<script>alert('�û��������������!!!');history.go(-1);</script>");
			// out.print("<script>alert('�û��������������!!!');location.href='index.jsp';</script>");
			// response.sendRedirect("index.jsp");
		} else {
			// ת��
			// ��Ҫת����ֵ���浽������
			// request.setAttribute("user", user);
			// ���������Ҫÿ��ҳ�湲��,��ô�ͽ������session��
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
