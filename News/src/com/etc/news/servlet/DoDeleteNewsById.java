package com.etc.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.news.biz.NewsBiz;
import com.etc.news.bizimpl.NewsBizImpl;

public class DoDeleteNewsById extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoDeleteNewsById() {
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

		// ���ñ����ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ��ȡ���ݹ�����newsid
		String newsid = request.getParameter("newsid");
		// ����newsidɾ�����ݿ������
		// �����Ƿ�ɾ���ɹ���ת����Ӧ��ҳ��
		NewsBiz biz = new NewsBizImpl();
		PrintWriter out = response.getWriter();
		if (biz.deleteNewsById(Integer.valueOf(newsid))) {
			// ɾ���ɹ�
			out.print("<script>alert('ɾ���ɹ�');location.href='DoQueryNewsPage';</script>");
		} else {
			// ɾ��ʧ��
			out.print("<script>alert('ɾ��ʧ��');history.go(-1);</script>");
		}
		out.flush();
		out.close();
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
