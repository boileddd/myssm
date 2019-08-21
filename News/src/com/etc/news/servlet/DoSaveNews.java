package com.etc.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.news.biz.NewsBiz;
import com.etc.news.bizimpl.NewsBizImpl;
import com.etc.news.entity.News;
import com.etc.news.entity.Type;
import com.etc.news.entity.User;
import com.etc.news.util.FileUp;
import com.etc.news.util.RenamePolicyCos;
import com.oreilly.servlet.MultipartRequest;

public class DoSaveNews extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoSaveNews() {
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
		// �趨��ǰ�ļ��ϴ���Ŀ���ļ���
		String savePath = this.getServletContext().getRealPath("/upload");

		// �趨�ļ��ϴ���������� 100M
		int maxSize = 10 * 10 * 1024 * 1024;
		// �����ļ��ϴ����������
		MultipartRequest multipartRequest = new MultipartRequest(request, savePath, maxSize, "utf-8",
				new RenamePolicyCos());

		// ��ȡ�����ݹ�����ֵ
		String newsId = multipartRequest.getParameter("newsid");
		String newstitle = multipartRequest.getParameter("newstitle");
		String newscontent = multipartRequest.getParameter("newscontent");
		String userId = multipartRequest.getParameter("newsauthor");
		String typeId = multipartRequest.getParameter("newstype");
		String files = "";
		try {
			files = FileUp.uploadfiles(multipartRequest);
			if (files == null) {
				files = multipartRequest.getParameter("newsimage");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = response.getWriter();

		// ����news_id���޸���Ӧ������

		NewsBiz biz = new NewsBizImpl();
		// ���������ݷ�װ��news����
		News news = new News();
		news.setNews_id(Integer.valueOf(newsId));
		news.setNews_title(newstitle);
		news.setNews_content(newscontent);
		// �����ϴ�������ͼƬ����
		news.setNews_image(files);
		// ����
		User user = new User();
		user.setUser_id(Integer.valueOf(userId));
		news.setNews_author(user);
		// ��������
		Type type = new Type();
		type.setType_id(Integer.valueOf(typeId));
		news.setNews_type(type);

		// ʱ��
		Date date = new Date(); // util date ����ʱ����������
		DateFormat df = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		news.setNews_time(df.format(date));

		if (biz.updateNewsById(news)) {
			out.print("<script>alert('����ɹ�!!!');location.href='DoQueryNewsPage';</script>");

		} else {
			out.print("<script>alert('����ʧ��!!!');history.go(-1);</script>");

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
