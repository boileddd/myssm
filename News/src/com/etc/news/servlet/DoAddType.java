package com.etc.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.news.biz.TypeBiz;
import com.etc.news.bizimpl.TypeBizImpl;

public class DoAddType extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public DoAddType() {
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
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//����addType���ݹ����Ĳ���
		//String encoding=this.getInitParameter("encoding");
		String encoding=this.getServletContext().getInitParameter("contextencoding");
		request.setCharacterEncoding(encoding);
		//�õ�����������
		String typename=request.getParameter("typename");
		//����ҵ���߼���  service  biz
		TypeBiz biz=new TypeBizImpl();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		  if(biz.addType(typename)){
		  out.print("<script>alert('��ӳɹ�');location.href='addType.jsp';</script>");
		  }else{
		    out.print("<script>alert('���ʧ��');history.back();</script>");
		  }
		  out.flush();
		  out.close();
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
